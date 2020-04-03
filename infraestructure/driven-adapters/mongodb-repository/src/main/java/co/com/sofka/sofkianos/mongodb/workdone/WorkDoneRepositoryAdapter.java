package co.com.sofka.sofkianos.mongodb.workdone;

import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public class WorkDoneRepositoryAdapter extends AdapterOperations<WorkDone, WorkDoneData, String, WorkDoneDataRepository> implements WorkDoneRepository {

    @Autowired
    public WorkDoneRepositoryAdapter(WorkDoneDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, WorkDone.WorkDoneBuilder.class).build());
    }

    @Override
    public Mono<WorkDone> findByIdWorkDone(String id) {
        return doQuery(repository.findById(id));
    }


    @Override
    public Mono<String> deleteById(String id) {
        return repository.deleteById(id).map(aVoid -> id);
    }

    @Override
    public Flux<WorkDone> findByIdSofkianoAndDateBetween(String idSofkiano, Date starDate, Date endDate) {
        return doQueryMany(repository.findByIdSofkianoAndDateBetween(idSofkiano, starDate, endDate));
    }

    @Override
    public Flux<WorkDone> findByIdSofianoAndDate(String idSofkiano, Date date) {
        return doQueryMany(repository.findByIdSofkianoAndDate(idSofkiano, date));
    }


}
