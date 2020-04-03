package co.com.sofka.sofkianos.usecase.projects;

import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import static co.com.sofka.sofkianos.domain.commons.UniqueIDGenerator.uuid;

import java.util.List;

@AllArgsConstructor
public class ActionProjectsUseCase {

    private final ProjectsRepository projectsRepository;
    private final CompaniesRepository companiesRepository;

    public Mono<Project> saveProject(Project project) {
        return projectsRepository.saveProject(project);
    }

    public Mono<Project> saveProject(String name, String nameCompany, List<String> service) {
        return uuid()
                .flatMap(id -> Mono.just(Project.builder().id(id).name(name).service(service).build()))
                .flatMap(project -> companiesRepository.findByName(nameCompany)
                        .flatMap(company -> Mono.just(project
                                .toBuilder()
                                .idCompany(company.getId())
                                .nameCompany(company.getName())
                                .build()) ))
                .flatMap(projectsRepository::saveProject);
    }
}
