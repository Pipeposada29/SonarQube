package co.com.sofka.sofkianos.domain.businessgroup;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class BusinessGroup {

    private final String id;
    private final String name;

}
