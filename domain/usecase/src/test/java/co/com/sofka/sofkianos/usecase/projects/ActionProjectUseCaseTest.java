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
import reactor.test.StepVerifier;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ActionProjectUseCaseTest {


    @InjectMocks
    ActionProjectsUseCase actionProjectsUseCase;

    @Mock
    ProjectsRepository projectsRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveProjectTest() {

        Project project = Project.builder()
                .id("1")
                .name("OurSofka")
                .idCompany("1")
                .service(List.of("Develop"))
                .build();

        Mockito.when(projectsRepository.saveProject(project)).thenReturn(Mono.just(project));
        StepVerifier.create(actionProjectsUseCase.saveProject(project))
                .expectNext(project)
                .verifyComplete();

    }
}