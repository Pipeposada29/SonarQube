package co.com.sofka.sofkianos.domain.businessgroup.gateway;


import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.domain.companies.Company;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessGroupRepository {

    Flux<BusinessGroup> findAllBusinessGroup();

    Mono<BusinessGroup> saveBusinessGroup(BusinessGroup businessGroup);

    Mono<Company> findByCompanies(String nameCompany);
}
