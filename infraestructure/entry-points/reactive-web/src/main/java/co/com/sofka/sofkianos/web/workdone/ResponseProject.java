package co.com.sofka.sofkianos.web.workdone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ResponseProject {
    String idProject;
    String name;
}
