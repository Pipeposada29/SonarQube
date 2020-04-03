package co.com.sofka.sofkianos.mongodb.workdone;

import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.crypto.Data;
import java.util.Date;

@Repository
public interface WorkDoneDataRepository extends ReactiveCrudRepository<WorkDoneData, String>, ReactiveQueryByExampleExecutor<WorkDoneData> {

    Mono<Void> deleteById(String id);

    Flux<WorkDoneData> findByIdSofkianoAndDateBetween(String idSofkiano, Date startDate, Date endDate);

    Flux<WorkDoneData> findByIdSofkianoAndDate(String idSofkiano, Date date);

}
