package co.com.sofka.sofkianos.domain.paramenters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Parameter {

    private final String name;
    private final List<String> values;
}
