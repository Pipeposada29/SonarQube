package co.com.sofka.sofkianos.mongodb.projects;

import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class ProjectsRepositoryAdapterTest {



    @InjectMocks
    ProjectsRepositoryAdapter projectsRepositoryAdapter;
    @Mock
    ProjectsDataRepository projectsDataRepository;

    @Mock
    private ObjectMapper mapper;






    @Before
    public void setUp()  {
    }

    Project project  = Project.builder()
            .id("1")
            .idCompany("1")
            .name("OurSofka")
            .nameCompany("Sofka")
            .service(Arrays.asList("Desarrollo","QA"))
            .build();

    private Mono<Project> projectMono = Mono.just(project);

    private final ProjectsData projectsData1 = new ProjectsData();

    @Test
    public void saveProjectTest(){

        ProjectsData projectsData = new ProjectsData();
        projectsData.setId("1");
        projectsData.setIdCompany("1");
        projectsData.setName("OurSofka");
        projectsData.setNameCompany("Sofka");
        projectsData.setService(Arrays.asList("Desarrollo" , "QA"));

        Mockito.when(mapper.map(any(Project.class),
                eq(ProjectsData.class))).thenReturn(projectsData);
        Mockito.when(projectsDataRepository.save(projectsData))
                .thenReturn(Mono.just(projectsData));

        StepVerifier.create(projectsRepositoryAdapter.saveProject(project))
                .expectNext(project)
                .verifyComplete();
    }


}