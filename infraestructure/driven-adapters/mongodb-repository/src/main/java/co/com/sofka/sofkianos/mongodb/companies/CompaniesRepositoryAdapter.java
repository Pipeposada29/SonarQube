package co.com.sofka.sofkianos.mongodb.companies;

import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompaniesRepositoryAdapter extends AdapterOperations<Company, CompanyData, String, CompaniesDataRepository>
        implements CompaniesRepository {

    @Autowired
    public CompaniesRepositoryAdapter(CompaniesDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, companyData -> mapper.mapBuilder(companyData, Company.CompanyBuilder.class).build());
    }


    @Override
    public Mono<List<Company>> saveAllCompanies(List<Company> list) {

        List<CompanyData> listData = list.stream()
                .map(this::toData)
                .collect(Collectors.toList());

        return repository.saveAll(listData)
                .map(companyData -> this.toEntity(companyData))
                .collectList();
    }

    @Override
    public Mono<Company> findByName(String nameCompany) {
        return doQuery(repository.findByName(nameCompany));
    }

    @Override
    public Flux<Company> findAll() {
        return doQueryMany(repository.findAll());
    }


}
