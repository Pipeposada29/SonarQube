package co.com.sofka.sofkianos.mongodb.sofkiano;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AssignmentData {
    private double assigmentPercent;
    private Boolean isBillable;
    private String projectId;
    private String projectName;
    private double rate;
    private String rateType;
    private Date releaseDate;
    private String service;
    private Date startDate;
}
