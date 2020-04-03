package co.com.sofka.sofkianos.mongodb.sofkiano;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class InformationSofkianoData {

    private Boolean actualInfo;
    private double salary;
    private double flexibleSalary;
    private Date dataInsertInfo;
    private Date entryCompanyDate;
    private String typeOfContract;
    private String company;
    private String position;
    private String area;
    private String office;
}
