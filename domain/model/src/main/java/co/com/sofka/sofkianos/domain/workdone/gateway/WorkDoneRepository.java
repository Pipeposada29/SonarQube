package co.com.sofka.sofkianos.domain.workdone.gateway;

import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface WorkDoneRepository {

    Mono<WorkDone> save(WorkDone workDone);

    Mono<WorkDone> findByIdWorkDone(String id);

    Mono<String> deleteById(String id);

    Flux<WorkDone> findByIdSofkianoAndDateBetween(String idSofkiano, Date startDate, Date endDate);

    Flux<WorkDone> findByIdSofianoAndDate(String idSofkiano, Date date);

}
