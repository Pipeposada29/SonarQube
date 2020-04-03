package co.com.sofka.sofkianos.mongodb.sofkiano;

import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SofkianoDataRepository extends ReactiveCrudRepository<SofkianoData, String>, ReactiveQueryByExampleExecutor<SofkianoData> {

    Mono<Sofkiano> findSofkianoDataByTypeDocumentAndDocument(String typeDocument, String document);

    Mono<Sofkiano> findByUid(String uid);

    Mono<SofkianoData> findByEmail (String email);

}
