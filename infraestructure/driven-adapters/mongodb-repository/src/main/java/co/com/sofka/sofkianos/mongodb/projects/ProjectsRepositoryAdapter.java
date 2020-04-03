package co.com.sofka.sofkianos.mongodb.projects;

import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProjectsRepositoryAdapter extends AdapterOperations<Project, ProjectsData, String, ProjectsDataRepository> implements ProjectsRepository {

    @Autowired
    public ProjectsRepositoryAdapter(ProjectsDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, projectsData -> mapper.mapBuilder(projectsData, Project.ProjectBuilder.class ).build());
    }

    @Override
    public Flux<Project> findAllProjects() {
        return null;
    }

    @Override
    public Mono<Project> findByIdProjects(String idProject) {
        return doQuery(repository.findById(idProject));
    }

    @Override
    public Mono<Project> saveProject(Project project) {
        return save(project);
    }

    @Override
    public Mono<Project> findProjectByName(String projectName) {
        return doQuery(repository.findByName(projectName));
    }

    @Override
    public Flux<Project> findByIdCompany(String idCompany) {
        return doQueryMany(repository.findByIdCompany(idCompany));
    }
}
