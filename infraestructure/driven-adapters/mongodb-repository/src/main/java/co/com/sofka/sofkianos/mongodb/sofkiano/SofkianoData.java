package co.com.sofka.sofkianos.mongodb.sofkiano;

import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.InformationSofkiano;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Sofkianos")
@NoArgsConstructor
public class SofkianoData {

    @Id
    private String id;
    private String document;
    private String typeDocument;
    private String uid;
    private String gender;
    private String name;
    private String email;
    private String pictureUrl;
    private String rol;
    private Date lastEntry;
    private List<InformationSofkiano> informationSofkianos;
    private List<Assignment> assignment;
}
