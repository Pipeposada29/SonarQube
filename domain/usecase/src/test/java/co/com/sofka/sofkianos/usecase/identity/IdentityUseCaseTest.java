package co.com.sofka.sofkianos.usecase.identity;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import co.com.sofka.sofkianos.domain.identity.Roles;
import co.com.sofka.sofkianos.domain.identity.gateway.IdentityRepository;
import co.com.sofka.sofkianos.domain.paramenters.Parameter;
import co.com.sofka.sofkianos.domain.paramenters.ParametersNames;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.Date;


@RunWith(MockitoJUnitRunner.Silent.class)
public class IdentityUseCaseTest {


    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private SofkianoRepository sofkianoRepository;

    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private IdentityUseCase underTest;

    @Test
    public void shouldSingUpTokenEmpty() {
        var tokenId = "";
        StepVerifier.create( underTest.singUp(tokenId)).expectError(Exception.class).verify();
        Mockito.verify(identityRepository, Mockito.times(0)).verifyTokenId(tokenId);
        Mockito.verify(sofkianoRepository, Mockito.times(0)).findSofkianoByEmail(ArgumentMatchers.anyString());
        Mockito.verify(sofkianoRepository, Mockito.times(0)).saveSofkiano(ArgumentMatchers.any(Sofkiano.class));
        Mockito.verify(parametersRepository, Mockito.times(0)).findParameterByName(GenericData.getAlphaNumericString(10));

    }

    @Test
    public void shouldSingUpTokenNull() {
        Assertions.assertThrows(NullPointerException.class, () -> underTest.singUp(null));
        Mockito.verify(identityRepository, Mockito.times(0)).verifyTokenId(null);
        Mockito.verify(sofkianoRepository, Mockito.times(0)).findSofkianoByEmail(ArgumentMatchers.anyString());
        Mockito.verify(sofkianoRepository, Mockito.times(0)).saveSofkiano(ArgumentMatchers.any(Sofkiano.class));
    }

    @Test
    public void shouldSingUpTokenInvalid() {
        var tokenId = "123456";

        Mockito.when(identityRepository.verifyTokenId(tokenId)).thenReturn(Mono.error(Exception::new));

        StepVerifier.create( underTest.singUp(tokenId)).expectError(Exception.class).verify();

        Mockito.verify(identityRepository, Mockito.times(1)).verifyTokenId(tokenId);
        Mockito.verify(sofkianoRepository, Mockito.times(0)).findSofkianoByEmail(ArgumentMatchers.anyString());
        Mockito.verify(sofkianoRepository, Mockito.times(0)).saveSofkiano(ArgumentMatchers.any(Sofkiano.class));

    }

    @Test
    public void shouldSingUpTokenValid() {

        var tokenId = "123456";
        var email = "mail@mail.com";
        var sofkiano = Sofkiano.builder()
                .document("")
                .typeDocument("")
                .name(GenericData.getAlphaNumericString(10))
                .gender("")
                .assignment(Collections.emptyList())
                .informationSofkianos(Collections.emptyList())
                .email(email)
                .uid(GenericData.getAlphaNumericString(10))
                .pictureUrl(GenericData.getAlphaNumericString(10))
                .lastEntry(new Date())
                .build();

        var sofkiano2 = Sofkiano.builder()
                .document("")
                .typeDocument("")
                .name(GenericData.getAlphaNumericString(10))
                .gender("")
                .rol(Roles.COLLABORATOR.getRol())
                .assignment(Collections.emptyList())
                .informationSofkianos(Collections.emptyList())
                .email(email)
                .uid(GenericData.getAlphaNumericString(10))
                .pictureUrl(GenericData.getAlphaNumericString(10))
                .lastEntry(new Date())
                .build();

        var identity = IdentityInfo.builder()
                .email(email)
                .enabled(true)
                .name(GenericData.getAlphaNumericString(10))
                .pictureUrl(GenericData.getAlphaNumericString(10))
                .uid(GenericData.getAlphaNumericString(10))
                .validated(true)
                .build();

        var listValues = Collections.singletonList("mail.com");


        var parameter = Parameter.builder().name(ParametersNames.COLLABORATORS_DOMAINS.getParameterName()).values(listValues).build();

        Mockito.when(identityRepository.verifyTokenId(tokenId)).thenReturn(Mono.just(identity));
        Mockito.when(sofkianoRepository.findSofkianoByEmail(email)).thenReturn(Mono.just(sofkiano));
        Mockito.when(sofkianoRepository.saveSofkiano(ArgumentMatchers.any(Sofkiano.class))).thenReturn(Mono.just(sofkiano2));
        Mockito.when(parametersRepository.findParameterByName(ParametersNames.COLLABORATORS_DOMAINS.getParameterName())).thenReturn(Mono.just(parameter));


        StepVerifier.create( underTest.singUp(tokenId)).assertNext(identityInfo -> {
            Assert.assertNotNull(identityInfo);
            Assert.assertEquals(email, identityInfo.getEmail());
            Assert.assertTrue(identityInfo.getRol() != null && !identityInfo.getRol().isEmpty());
        }).verifyComplete();

        Mockito.verify(identityRepository, Mockito.times(1)).verifyTokenId(tokenId);
        Mockito.verify(sofkianoRepository, Mockito.times(1)).findSofkianoByEmail(ArgumentMatchers.anyString());
        Mockito.verify(sofkianoRepository, Mockito.times(1)).saveSofkiano(ArgumentMatchers.any(Sofkiano.class));
        Mockito.verify(parametersRepository, Mockito.times(1)).findParameterByName(ArgumentMatchers.anyString());

    }


}