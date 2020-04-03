package co.com.sofka.sofkianos.mongodb.parameters;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ParametersDataRepository extends ReactiveCrudRepository<ParameterData, String>, ReactiveQueryByExampleExecutor<ParameterData> {
    Mono<ParameterData> findByName(String nameParameter);
}
