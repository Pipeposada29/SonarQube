package co.com.sofka.sofkianos.mongodb.companies;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CompaniesDataRepository extends ReactiveCrudRepository<CompanyData, String>, ReactiveQueryByExampleExecutor<CompanyData> {
    Mono<CompanyData> findByName (String name);

}
