package co.com.sofka.sofkianos.domain.companies.gateway;

import co.com.sofka.sofkianos.domain.companies.Company;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CompaniesRepository {

    Mono<List<Company>> saveAllCompanies(List<Company> list);

    Mono<Company> findByName(String nameCompany);

    Flux<Company> findAll();

    Mono<Company> findById(String id);
}
