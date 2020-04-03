package co.com.sofka.sofkianos.domain.workdone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class WorkDone {
    private final String id;
    private final String idSofkiano;
    private final String uid;
    private final String nameSofkiano;
    private final String idProject;
    private final Date date;
    private final String nameProject;
    private final String description;
    private final int hours;
    private final int minutes;
    private final String type;
    private final double hourlyRate;
}
