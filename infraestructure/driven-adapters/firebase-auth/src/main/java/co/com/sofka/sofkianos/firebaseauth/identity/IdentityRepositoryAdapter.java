package co.com.sofka.sofkianos.firebaseauth.identity;

import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import co.com.sofka.sofkianos.domain.identity.gateway.IdentityRepository;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.springframework.util.StringUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;


@Log
@Component
@RequiredArgsConstructor
public class IdentityRepositoryAdapter implements IdentityRepository {


    @Override
    public Mono<IdentityInfo> verifyTokenId(String firebaseToken) {


        if (StringUtils.isEmpty(firebaseToken)) {
            throw new IllegalArgumentException("FirebaseTokenBlank");
        }

        FirebaseToken token = null;
        try {

            token = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
        } catch (FirebaseAuthException e) {
            return Mono.error(e);
        }

        return Mono.just(IdentityInfo.builder()
                    .uid(token.getUid())
                    .name(token.getName())
                    .email(token.getEmail())
                    .pictureUrl(token.getPicture())
                    .build());




    }
}
