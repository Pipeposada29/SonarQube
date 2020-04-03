package co.com.sofka.sofkianos.usecase.parameters;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.paramenters.Parameter;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.Silent.class)
public class QueryParametersUseCaseTest {


    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private QueryParametersUseCase underTest;

    @Test
    public void shouldGetParametersByName() {

        var parameterName = GenericData.getAlphaNumericString(10);
        var parameterVale = GenericData.getAlphaNumericString(10);

        Mockito.when(parametersRepository.findParameterByName(parameterName)).thenReturn(Mono.just(Parameter.builder().name(parameterName).values(Arrays.asList(parameterVale)).build()));

        StepVerifier.create( underTest.getParameters(parameterName)).assertNext(parameter -> {
            Assert.assertNotNull(parameter);
            Assert.assertTrue(parameter.getValues().stream().count() > 0);
        }).verifyComplete();


        Mockito.verify(parametersRepository, Mockito.times(1)).findParameterByName(parameterName);



    }


}