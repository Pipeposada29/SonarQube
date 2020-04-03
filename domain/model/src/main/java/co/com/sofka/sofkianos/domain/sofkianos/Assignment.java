
package co.com.sofka.sofkianos.domain.sofkianos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Assignment {

    private final double assigmentPercent;
    private final Boolean isBillable;
    private final String projectId;
    private final String projectName;
    private final double rate;
    private final String rateType;
    private final Date releaseDate;
    private final String service;
    private final Date startDate;

}
