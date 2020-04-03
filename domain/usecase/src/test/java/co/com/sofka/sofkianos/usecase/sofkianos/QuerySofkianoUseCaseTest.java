package co.com.sofka.sofkianos.usecase.sofkianos;

import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.InformationSofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class QuerySofkianoUseCaseTest {


    @InjectMocks
    private QuerySofkianoUseCase querySofkianoUseCase;

    @Mock
    private SofkianoRepository sofkianoRepository;

    @Before
    public void setUp() throws Exception {
    }

    private final Date date1 = new Date(2020, 03, 24);
    private final Date date2 = new Date(2020, 03, 23);

    private InformationSofkiano informationSofkiano = InformationSofkiano.builder()
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
    private Mono<InformationSofkiano> informationSofkianoMono = Mono.just(informationSofkiano);

    private Assignment assignment = Assignment.builder()
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

    private Mono<Assignment> assignmentMono = Mono.just(assignment);

    private Sofkiano sofkiano = Sofkiano.builder()
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
    private Mono<Sofkiano> sofkianoMono  = Mono.just(sofkiano);

    @Test
    public void findSofkianoByIdTest() {
        Mockito.when(sofkianoRepository.findSofkianoById(sofkiano.getId())).thenReturn(sofkianoMono);
        Mono<Sofkiano> sofkianoReturn = querySofkianoUseCase.findSofkianoById(sofkiano.getId());
        assertEquals(sofkianoMono, sofkianoReturn);
    }

    @Test
    public void getSofkianoByTypeDocumentAndDocument() {
        Mockito.when(sofkianoRepository.findByTypeDocumentAndDocument(sofkiano.getTypeDocument(),sofkiano.getDocument()))
                .thenReturn(sofkianoMono);
        Mono<Sofkiano> sofkianoReturn = querySofkianoUseCase.getSofkianoByTypeDocumentAndDocument(sofkiano.getTypeDocument(),sofkiano.getDocument());
        assertEquals(sofkianoMono,sofkianoReturn);
    }
}