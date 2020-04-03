package co.com.sofka.sofkianos.mongodb.workdone;

import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.InformationSofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WorkDoneRepositoryAdapterTest {


    @InjectMocks
    WorkDoneRepositoryAdapter workDoneRepositoryAdapter;

    @Mock
    WorkDoneDataRepository workDoneDataRepository;

    @Mock
    private ObjectMapper mapper;

    private final Date date1 = new Date(2020, 03, 24);
    private final Date date2 = new Date(2020, 03, 23);

    WorkDone workDone = WorkDone.builder()
            .date(date1)
            .idSofkiano("1")
            .nameSofkiano("John Felipe")
            .nameProject("OurSofka")
            .type("Desarollo")
            .description("Test")
            .id("1")
            .hourlyRate(10000)
            .hours(9)
            .idProject("2")
            .uid("12")
            .build();


    InformationSofkiano informationSofkiano = InformationSofkiano.builder()
            .actualInfo(true)
            .area("Tecnica")
            .company("SFS CONSULTORES")
            .dataInsertInfo(new Date())
            .entryCompanyDate(date1)
            .flexibleSalary(500000)
            .office("Medellin")
            .position("Desarrollador Junior")
            .typeOfContract("Termino Indefinido")
            .salary(1000000)
            .build();


    Assignment assignment = Assignment.builder()
            .releaseDate(date2)
            .projectId("1")
            .isBillable(true)
            .assigmentPercent(100)
            .rate(10000)
            .projectName("OurSofka")
            .service("Desarrollo")
            .startDate(new Date())
            .rateType("Hours")
            .build();


    Sofkiano sofkiano = Sofkiano.builder()
            .id("1")
            .email("John.posada@sofka.com.co")
            .uid("1")
            .name("John Felipe Posada")
            .typeDocument("CC")
            .gender("M")
            .document("1152710052")
            .pictureUrl("/documentos/123")
            .informationSofkianos(List.of(informationSofkiano))
            .assignment(List.of(assignment))
            .build();
    WorkDoneData workDoneData = new WorkDoneData();

    @Before
    public void setUp() {
    }


    @Test
    public void deleteById() {
        Mockito.when(workDoneDataRepository.deleteById(workDone.getId())).thenReturn(Mono.empty());
        StepVerifier.create(workDoneRepositoryAdapter.deleteById("1"))
                .expectNext()
                .verifyComplete();


    }
}