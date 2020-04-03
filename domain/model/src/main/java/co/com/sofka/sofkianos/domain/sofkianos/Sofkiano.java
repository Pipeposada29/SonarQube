package co.com.sofka.sofkianos.domain.sofkianos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Sofkiano {
    private final String id;
    private final String document;
    private final String typeDocument;
    private final String uid;
    private final String gender;
    private final String name;
    private final String email;
    private final String pictureUrl;
    private final String rol;
    private final Date lastEntry;
    private final List<InformationSofkiano> informationSofkianos;
    private final List<Assignment> assignment;

}
