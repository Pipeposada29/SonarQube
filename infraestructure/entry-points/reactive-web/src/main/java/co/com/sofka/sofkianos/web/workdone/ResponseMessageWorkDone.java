package co.com.sofka.sofkianos.web.workdone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseMessageWorkDone {

    String function;
    String message;
    int code;
}