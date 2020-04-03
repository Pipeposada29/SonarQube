package co.com.sofka.sofkianos.web.identity;

import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import co.com.sofka.sofkianos.usecase.identity.IdentityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin( origins = "*")
@RestController
@RequestMapping(value = "/identity", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityUseCase identityUseCase;

    @CrossOrigin("*")
    @GetMapping(path = "singIn")
    public Mono<IdentityInfo> singIn (@RequestParam(name = "firebaseToken") String firebaseToken) {
        return identityUseCase.singUp(firebaseToken);
    }
}
