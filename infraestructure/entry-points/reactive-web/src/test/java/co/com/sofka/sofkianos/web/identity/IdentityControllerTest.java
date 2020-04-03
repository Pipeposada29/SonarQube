package co.com.sofka.sofkianos.web.identity;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import co.com.sofka.sofkianos.usecase.identity.IdentityUseCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class IdentityControllerTest {

    @Mock
    private IdentityUseCase identityUseCase;

    @InjectMocks
    private IdentityController underTest;

    @Test
    public void shouldSingInSofkiano() {

        String firebaseToken = GenericData.getAlphaNumericString(10);

        Mockito.when(identityUseCase.singUp(firebaseToken)).thenReturn(Mono.just(IdentityInfo.builder().email(firebaseToken).build()));

        StepVerifier.create(underTest.singIn(firebaseToken)).assertNext(identityInfo -> {
            Assert.assertNotNull(identityInfo);
            Assert.assertEquals("xxx", identityInfo.getEmail());
        });

        Mockito.verify(identityUseCase,Mockito.times(1)).singUp(firebaseToken);

    }

    @Test
    public void shouldSingInSofkianoFirebaseTokenEmpty() {

        String firebaseToken = "";

        Mockito.when(identityUseCase.singUp(firebaseToken)).thenReturn(Mono.error(new Exception("Is Empty")));

        StepVerifier.create(underTest.singIn(firebaseToken)).expectError(Exception.class).verify();

        Mockito.verify(identityUseCase,Mockito.times(1)).singUp(firebaseToken);

    }

    @Test
    public void shouldSingInSofkianoFirebaseTokenNull() {

        String firebaseToken = null;

        Mockito.when(identityUseCase.singUp(firebaseToken)).thenReturn(Mono.error(new NullPointerException("Is Empty")));

        StepVerifier.create(underTest.singIn(firebaseToken)).expectError(NullPointerException.class).verify();

        Mockito.verify(identityUseCase,Mockito.times(1)).singUp(firebaseToken);

    }
}