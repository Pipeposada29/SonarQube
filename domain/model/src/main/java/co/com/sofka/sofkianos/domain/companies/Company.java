package co.com.sofka.sofkianos.domain.companies;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Company {

    private final String id;
    private final String name;
    private final String idBusinessGroup;
    private final String nameBusinessGroup;



}
