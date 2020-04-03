package co.com.sofka.sofkianos.mongodb.workdone;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "WorkDone")
public class WorkDoneData {

    @Id
    private String id;
    private String idSofkiano;
    private String uid;
    private String nameSofkiano;
    private String idProject;
    private Date date;
    private String nameProject;
    private String description;
    private int hours;
    private int minutes;
    private String type;
    private double hourlyRate;
}
