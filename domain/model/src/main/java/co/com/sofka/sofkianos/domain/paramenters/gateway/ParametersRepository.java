package co.com.sofka.sofkianos.domain.paramenters.gateway;

import co.com.sofka.sofkianos.domain.paramenters.Parameter;
import reactor.core.publisher.Mono;

public interface ParametersRepository {
    Mono<Parameter> findParameterByName(String nameParameter);
}
