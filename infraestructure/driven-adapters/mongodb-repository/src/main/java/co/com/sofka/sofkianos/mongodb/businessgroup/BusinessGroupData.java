package co.com.sofka.sofkianos.mongodb.businessgroup;

import co.com.sofka.sofkianos.domain.companies.Company;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "BusinessGroup")
public class BusinessGroupData {
    @Id
    private String id;
    private String name;
    private List<Company> companies;
}
