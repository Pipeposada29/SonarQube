package co.com.sofka.sofkianos.usecase.projects;

import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QueryProjectUseCaseTest {


    @InjectMocks
    QueryProjectsUseCase queryProjectsUseCase;

    @Mock
    ProjectsRepository projectsRepository;


    @BeforeEach
    void setUp() {
    }


     private final Project project = Project.builder()
            .id("1")
            .name("OurSofka")
            .idCompany("1")
            .service(List.of("Develop"))
            .build();

    Mono<Project> projectsMono = Mono.just(project);
    @Test
    public void shouldFindProjectById() {

        Mockito.when(projectsRepository.findByIdProjects(project.getId())).thenReturn(projectsMono);
        Mono<Project> returnedProject = queryProjectsUseCase.findByIdProject(project.getId());

        assertEquals(projectsMono, returnedProject);


    }

}