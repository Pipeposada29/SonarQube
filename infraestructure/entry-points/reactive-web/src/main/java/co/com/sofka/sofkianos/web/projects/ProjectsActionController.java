package co.com.sofka.sofkianos.web.projects;


import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.usecase.projects.ActionProjectsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProjectsActionController {

    private final ActionProjectsUseCase actionProjectsUseCase;

    @PostMapping(path = "/register")
    public Mono<Project> saveProject(@RequestBody ProjectsRequest projectsRequest){
        return actionProjectsUseCase.saveProject(projectsRequest.getName(), projectsRequest.getNameCompany(), projectsRequest.getService());
    }
}
