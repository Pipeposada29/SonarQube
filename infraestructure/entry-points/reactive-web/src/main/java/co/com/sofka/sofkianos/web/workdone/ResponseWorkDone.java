package co.com.sofka.sofkianos.web.workdone;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseWorkDone {
    List<ResponseDaysWorkDone> days;
}
