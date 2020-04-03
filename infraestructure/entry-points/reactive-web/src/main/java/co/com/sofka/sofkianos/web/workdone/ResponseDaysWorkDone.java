package co.com.sofka.sofkianos.web.workdone;

import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor

public class ResponseDaysWorkDone {

    public long date;
    public int totalHours;
    public int totalMinutes;
    public List<WorkDone> workDoneList;


}
