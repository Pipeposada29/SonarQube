package co.com.sofka.sofkianos.usecase.businessgroup;

import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.businessgroup.gateway.BusinessGroupRepository;
import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.stream.Collectors;

import static co.com.sofka.sofkianos.domain.commons.UniqueIDGenerator.uuid;
import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

@RequiredArgsConstructor
public class ActionBusinessGroupUseCase {

    private final BusinessGroupRepository businessGroupRepository;
    private final CompaniesRepository companiesRepository;

    public Mono<Tuple2<BusinessGroup, List<Company>>> saveBusinessGroupAndCompanies(BusinessGroup businessGroup, List<Company> companyList) {

        return saveBusinessGroup(businessGroup)
                .flatMap(businessGroup1 -> saveListCompanies(businessGroup1, companyList)
                        .map(companies -> Tuples.of(businessGroup1, companies)));

    }

    private Mono<BusinessGroup> saveBusinessGroup(BusinessGroup businessGroup) {

        return uuid()
                .flatMap(id -> setId(id, businessGroup))
                .flatMap(businessGroupRepository::saveBusinessGroup);

    }

    private Mono<BusinessGroup> setId(String id, BusinessGroup businessGroup){
        return  id.isEmpty() ?  error(Exception::new) :
                        just(businessGroup.toBuilder().id(id).build());
    }


    private  Mono<List<Company>> saveListCompanies(BusinessGroup businessGroup, List<Company> companyList){

        var list = companyList.stream()
                .map(company ->  uuid()
                            .flatMap(id -> just(company
                                    .toBuilder()
                                    .id(id)
                                    .idBusinessGroup(businessGroup.getId())
                                    .nameBusinessGroup(businessGroup.getName())
                                    .build())).block()
                ).collect(Collectors.toList());

        return companiesRepository.saveAllCompanies(list);
    }
}
