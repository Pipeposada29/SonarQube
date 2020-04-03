package co.com.sofka.sofkianos.domain.projects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Project {

    private String id;
    private String idCompany;
    private String nameCompany;
    private String name;
    private List<String> service;
}
