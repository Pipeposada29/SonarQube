package co.com.sofka.sofkianos.mongodb.parameters;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Parameters")
public class ParameterData {
    private String name;
    private List<String> values;
}
