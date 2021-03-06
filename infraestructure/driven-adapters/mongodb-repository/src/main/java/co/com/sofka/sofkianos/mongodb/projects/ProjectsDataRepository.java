package co.com.sofka.sofkianos.mongodb.projects;


import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectsDataRepository extends ReactiveCrudRepository<ProjectsData, String>, ReactiveQueryByExampleExecutor<ProjectsData> {
    Mono<ProjectsData> findByName (String name);

    Flux<ProjectsData> findByIdCompany(String idCompany);

}
