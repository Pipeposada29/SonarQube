package co.com.sofka.sofkianos.web.projects;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyResponse {

    private String id;
    private String selectCompany;
    private List<String> projectsNames;
}
