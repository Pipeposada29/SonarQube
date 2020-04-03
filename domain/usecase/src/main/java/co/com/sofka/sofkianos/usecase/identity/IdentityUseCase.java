package co.com.sofka.sofkianos.usecase.identity;

import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import co.com.sofka.sofkianos.domain.identity.Roles;
import co.com.sofka.sofkianos.domain.identity.gateway.IdentityRepository;
import co.com.sofka.sofkianos.domain.paramenters.ParametersNames;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;


@RequiredArgsConstructor
public class IdentityUseCase {

    private final IdentityRepository identityRepository;
    private final SofkianoRepository sofkianoRepository;
    private final ParametersRepository parametersRepository;


    public Mono<IdentityInfo> singUp (String tokenId) {
               return Mono.just(tokenId)
                       .filter(s -> !s.isEmpty())
                       .switchIfEmpty(Mono.error(new Exception("Token Id is Empty")))
                       .flatMap(identityRepository::verifyTokenId)
                       .flatMap(this::saveSofkianoSingIn);
    }


    private Mono<IdentityInfo> saveSofkianoSingIn (IdentityInfo identityInfo) {

        return sofkianoRepository.findSofkianoByEmail(identityInfo.getEmail())
                .defaultIfEmpty(createSofkianoByIdentityInfo(identityInfo))
                .flatMap(this::getRol)
                .flatMap(sofkiano -> sofkianoRepository
                        .saveSofkiano(sofkiano.toBuilder()
                                .uid(identityInfo.getUid())
                                .pictureUrl(identityInfo.getPictureUrl())
                                .lastEntry(new Date())
                                .build()))
                .flatMap(sofkiano -> Mono.just(identityInfo.toBuilder().rol(sofkiano.getRol()).build()));

    }


    private Sofkiano createSofkianoByIdentityInfo(IdentityInfo identityInfo) {

        return Sofkiano.builder()
                .document("")
                .typeDocument("")
                .name(identityInfo.getName())
                .gender("")
                .assignment(Collections.emptyList())
                .informationSofkianos(Collections.emptyList())
                .email(identityInfo.getEmail())
                .uid(identityInfo.getUid())
                .pictureUrl(identityInfo.getPictureUrl())
                .lastEntry(new Date())
                .build();

    }



    private Mono<Sofkiano> getRol(Sofkiano sofkiano) {
        return (sofkiano.getRol() != null && !sofkiano.getRol().isEmpty()) ? Mono.just(sofkiano) :
                parametersRepository.findParameterByName(ParametersNames.COLLABORATORS_DOMAINS.getParameterName())
                        .map(parameter -> (parameter
                                .getValues()
                                .stream().anyMatch(parameterValue -> sofkiano
                                        .getEmail()
                                        .contains(parameterValue))) ? Roles.COLLABORATOR.getRol() : Roles.INVITED.getRol())
                        .map(rol -> sofkiano.toBuilder().rol(rol).build());
    }


}
