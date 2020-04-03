package co.com.sofka.sofkianos.web.projects;


import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.usecase.projects.QueryProjectsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin( origins = "*")
@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProjectsQueryController {

    private final QueryProjectsUseCase queryProjectsUseCase;


    @GetMapping
    public Flux<Project> getAllProjects() {
        return queryProjectsUseCase.findAllProjects();
    }

    @GetMapping(path = "/{idProject}")
    public Mono<Project> findByIdProject(@PathVariable("idProject") String idProject) {
        return queryProjectsUseCase.findByIdProject(idProject);
    }

    @GetMapping(path = "/companies")
    public Flux<CompanyResponse> findAllCompanies() {
        return queryProjectsUseCase.findAllCompanies()
                .map(tuple2 -> CompanyResponse
                        .builder()
                        .id(tuple2.getT2().getId())
                        .selectCompany(tuple2.getT2().getNameBusinessGroup().equals(tuple2.getT2().getName()) ? tuple2.getT2().getName() : tuple2.getT2().getNameBusinessGroup()+" - "+tuple2.getT2().getName())
                        .projectsNames(tuple2.getT1())
                        .build());
    }

}
