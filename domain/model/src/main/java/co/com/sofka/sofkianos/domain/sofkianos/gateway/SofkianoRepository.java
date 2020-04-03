package co.com.sofka.sofkianos.domain.sofkianos.gateway;

import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SofkianoRepository {

    Mono<Sofkiano> findSofkianoById(String id);

    Flux<Sofkiano> findAllSofkianos();

    Mono<Sofkiano> saveSofkiano(Sofkiano sofkiano);

    Mono<Sofkiano> findByTypeDocumentAndDocument(String typeDocument, String document);

    Mono<Sofkiano> saveAssignmentSofkiano( Assignment assignment);

    Mono<Sofkiano>  findSofkianoByUid(String uid);
    Mono<Sofkiano> findSofkianoByEmail(String email);

}
