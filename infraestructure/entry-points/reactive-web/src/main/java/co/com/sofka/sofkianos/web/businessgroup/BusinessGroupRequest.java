package co.com.sofka.sofkianos.web.businessgroup;

import lombok.Data;

import java.util.List;

@Data
public class BusinessGroupRequest {
    private String name;
    private List<CompaniesRequest> companies;
}
