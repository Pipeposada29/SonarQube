package co.com.sofka.sofkianos.usecase.sofkianos;

import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
public class QuerySofkianoUseCase {

    private final SofkianoRepository sofkianoRepository;

    public Mono<Sofkiano> findSofkianoById(String id) {
        return sofkianoRepository.findSofkianoById(id);
    }

    public Flux<Sofkiano> findAllSofkianos() {
        return sofkianoRepository.findAllSofkianos();
    }


    public Mono<Sofkiano> getSofkianoByTypeDocumentAndDocument(String typeDocument, String document) {
        return sofkianoRepository.findByTypeDocumentAndDocument(typeDocument, document);
    }
}

