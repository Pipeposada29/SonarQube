package co.com.sofka.sofkianos.web.businessgroup;

import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.usecase.businessgroup.ActionBusinessGroupUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin( origins = "*")
@RestController
@RequestMapping(value = "/businessgroups", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BusinessGroupActionController {

    private final ActionBusinessGroupUseCase actionBusinessGroupUseCase;

    @PostMapping(path = "/register")
    public Mono<BusinessGroupResponse> saveBusinessGroup(@RequestBody BusinessGroupRequest businessGroupRequest) {

        List<Company> companyList = businessGroupRequest.getCompanies().stream().map(companiesRequest -> Company.builder().name(companiesRequest.getNameClient()).build()).collect(Collectors.toList());


         return actionBusinessGroupUseCase.saveBusinessGroupAndCompanies(BusinessGroup.builder()
                .name(businessGroupRequest.getName())
                .build(), companyList )
                 .map(objects -> BusinessGroupResponse.builder()
                 .id(objects.getT1().getId())
                 .name(objects.getT1().getName())
                 .companies(objects.getT2())
                 .build());


    }
}
