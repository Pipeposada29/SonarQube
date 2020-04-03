package co.com.sofka.sofkianos.usecase.parameters;

import co.com.sofka.sofkianos.domain.paramenters.Parameter;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class QueryParametersUseCase {

    private final ParametersRepository parametersRepository;


    public Mono<Parameter> getParameters(String nameParameter) {
        return parametersRepository.findParameterByName(nameParameter);
    }

}
