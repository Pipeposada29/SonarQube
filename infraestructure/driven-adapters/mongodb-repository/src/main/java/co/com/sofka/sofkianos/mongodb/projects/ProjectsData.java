package co.com.sofka.sofkianos.mongodb.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "Projects")
public class ProjectsData {

    @Id
    private String id;
    private String idCompany;
    private String nameCompany;
    private String name;
    private List<String> service;
}
