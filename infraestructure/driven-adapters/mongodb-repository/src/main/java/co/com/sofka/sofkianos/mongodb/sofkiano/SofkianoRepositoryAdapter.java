package co.com.sofka.sofkianos.mongodb.sofkiano;

import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Repository
public class SofkianoRepositoryAdapter extends AdapterOperations<Sofkiano, SofkianoData, String, SofkianoDataRepository> implements SofkianoRepository {

    @Autowired
    public SofkianoRepositoryAdapter(SofkianoDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Sofkiano.SofkianoBuilder.class).build());
    }


    @Override
    public Mono<Sofkiano> findSofkianoById(String id) {
        return doQuery(repository.findById(id));
    }

    @Override
    public Flux<Sofkiano> findAllSofkianos() {
        return doQueryMany(repository.findAll());
    }

    @Override
    public Mono<Sofkiano> saveSofkiano(Sofkiano sofkiano) {
            return save(sofkiano);
    }

    @Override
    public Mono<Sofkiano> findByTypeDocumentAndDocument(String typeDocument, String document) {
        return repository.findSofkianoDataByTypeDocumentAndDocument(typeDocument, document);
    }

    @Override
    public Mono<Sofkiano> saveAssignmentSofkiano(Assignment assignment) {
        return save(Sofkiano.builder().assignment(Arrays.asList(assignment)).build());
    }

    @Override
    public Mono<Sofkiano> findSofkianoByUid(String uid) {
        return repository.findByUid(uid);
    }


    @Override
    public Mono<Sofkiano> findSofkianoByEmail(String email) {
        return doQuery(repository.findByEmail(email));
    }

}

