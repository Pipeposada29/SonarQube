package co.com.sofka.sofkianos.domain.projects;

import co.com.sofka.sofkianos.domain.companies.Company;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectsRepository {

    Flux<Project> findAllProjects();

    Mono<Project> findByIdProjects(String idProject);

    Mono<Project> saveProject(Project project);

    Mono<Project> findProjectByName(String projectName);

    Flux<Project> findByIdCompany(String idCompany);
}
