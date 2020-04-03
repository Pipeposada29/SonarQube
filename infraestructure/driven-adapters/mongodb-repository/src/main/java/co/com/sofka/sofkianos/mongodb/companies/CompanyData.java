package co.com.sofka.sofkianos.mongodb.companies;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@Document(collection = "Companies")
public class CompanyData {

    @Id
    private String id;
    private String name;
    private String idBusinessGroup;
    private String nameBusinessGroup;
}
