package co.com.sofka.sofkianos.web.workdone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
public class ResponseWorkDoneParameters {
    List<ResponseProject> projects;
    List<String> hoursTypes;
    List<String> servicesTypes;
}
