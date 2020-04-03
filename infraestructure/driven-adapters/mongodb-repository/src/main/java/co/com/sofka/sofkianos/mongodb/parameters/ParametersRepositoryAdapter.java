package co.com.sofka.sofkianos.mongodb.parameters;

import co.com.sofka.sofkianos.domain.paramenters.Parameter;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ParametersRepositoryAdapter extends AdapterOperations<Parameter, ParameterData, String, ParametersDataRepository> implements ParametersRepository {

    @Autowired
    public ParametersRepositoryAdapter(ParametersDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, parameterData -> mapper.mapBuilder(parameterData, Parameter.ParameterBuilder.class ).build());
    }

    @Override
    public Mono<Parameter> findParameterByName(String nameParameter) {
        return doQuery(repository.findByName(nameParameter));
    }
}
