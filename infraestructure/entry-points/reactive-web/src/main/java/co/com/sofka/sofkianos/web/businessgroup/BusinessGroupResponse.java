package co.com.sofka.sofkianos.web.businessgroup;

import co.com.sofka.sofkianos.domain.companies.Company;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusinessGroupResponse {

    private String id;
    private String name;
    private List<Company> companies;
}
