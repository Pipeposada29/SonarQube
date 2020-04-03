package co.com.sofka.sofkianos.domain.workdone;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class DaysWorkDone {
    public final Date date;
    public final int totalHours;
    public final int totalMinutes;
    public final List<WorkDone> workDoneList;
}
