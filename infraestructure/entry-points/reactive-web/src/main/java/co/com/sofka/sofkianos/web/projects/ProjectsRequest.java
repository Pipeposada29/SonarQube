package co.com.sofka.sofkianos.web.projects;

import lombok.Data;


import java.util.List;

@Data
public class ProjectsRequest {

    private String nameCompany;
    private String name;
    private List<String> service;
}
