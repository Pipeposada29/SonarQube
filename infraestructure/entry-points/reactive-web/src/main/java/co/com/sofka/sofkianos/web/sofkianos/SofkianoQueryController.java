package co.com.sofka.sofkianos.web.sofkianos;

import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.usecase.sofkianos.QuerySofkianoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin( origins = "*")
@RestController
@RequestMapping(value = "/sofkiano", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SofkianoQueryController {

    private final QuerySofkianoUseCase useCase;

    @GetMapping(path = "/{id}")
    public Mono<Sofkiano> getSofkiano(@PathVariable("id") String id) {
        return useCase.findSofkianoById(id);
    }


    @GetMapping
    public Flux<Sofkiano> getFindAllSofkianos() {
        return useCase.findAllSofkianos();
    }

    @GetMapping(path = "/{typeDocument}/{document}")
    public Mono<Sofkiano>getSofkianoByTypeDocumentAndDocument(@PathVariable(name = "typeDocument") String typeDocument,
                                                              @PathVariable(name = "document") String document){
        return useCase.getSofkianoByTypeDocumentAndDocument(typeDocument,document);

    }

}
