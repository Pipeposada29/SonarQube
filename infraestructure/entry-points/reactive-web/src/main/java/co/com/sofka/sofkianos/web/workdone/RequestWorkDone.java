package co.com.sofka.sofkianos.web.workdone;

import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import lombok.Data;

import java.util.Date;

@Data
public class RequestWorkDone {

    String description;
    long date;
    String idProject;
    int hours;
    String hoursType;
    int hourlyRate;
    String nameProject;
    int minutes;

    public WorkDone getWorkDone() {
        return WorkDone.builder().description(this.getDescription()).date(new Date(this.getDate())).type(this.hoursType)
                .hours(this.getHours()).minutes(this.getMinutes()).idProject(this.idProject).hourlyRate(this.hourlyRate).nameProject(this.nameProject).build();

    }
}
