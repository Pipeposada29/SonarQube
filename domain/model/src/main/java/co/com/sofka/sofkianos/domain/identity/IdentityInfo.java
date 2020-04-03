package co.com.sofka.sofkianos.domain.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class IdentityInfo {

    private final String email;
    private final String name;
    private final String uid;
    private final boolean enabled;
    private final boolean validated;
    private final String pictureUrl;
    private final String rol;

}

