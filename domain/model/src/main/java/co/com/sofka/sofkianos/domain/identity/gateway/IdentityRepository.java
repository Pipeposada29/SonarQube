package co.com.sofka.sofkianos.domain.identity.gateway;

import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import reactor.core.publisher.Mono;

public interface IdentityRepository {
    Mono<IdentityInfo> verifyTokenId(String tokenId);
}
