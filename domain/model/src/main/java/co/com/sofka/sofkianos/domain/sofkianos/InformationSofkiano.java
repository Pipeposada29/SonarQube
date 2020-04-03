package co.com.sofka.sofkianos.domain.sofkianos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InformationSofkiano {

    private final Boolean actualInfo;
    private final double salary;
    private final double flexibleSalary;
    private final Date dataInsertInfo;
    private final Date entryCompanyDate;
    private final String typeOfContract;
    private final String company;
    private final String position;
    private final String area;
    private final String office;

}
