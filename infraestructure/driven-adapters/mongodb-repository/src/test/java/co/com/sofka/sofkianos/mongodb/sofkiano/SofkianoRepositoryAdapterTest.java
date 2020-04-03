package co.com.sofka.sofkianos.mongodb.sofkiano;

import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.InformationSofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class SofkianoRepositoryAdapterTest {


    @InjectMocks
    SofkianoRepositoryAdapter sofkianoRepositoryAdapter;

    @Mock
    SofkianoDataRepository sofkianoDataRepository;

    @Mock
    private ObjectMapper mapper;


    @Before
    public void setUp() throws Exception {
    }


    private final Date date1 = new Date(2020, 03, 24);
    private final Date date2 = new Date(2020, 03, 23);

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

    Mono<Sofkiano> sofkianoMono  = Mono.just(sofkiano);






    @Test
    public void saveSofkianoTest() {


        Mono<Sofkiano> sofkianoMono  = Mono.just(sofkiano);

        SofkianoData sofkianoData = new SofkianoData();
        sofkianoData.setId("1");
        sofkianoData.setEmail("John.posada@sofka.com.co");
        sofkianoData.setUid("1");
        sofkianoData.setName("John Felipe Posada");
        sofkianoData.setTypeDocument("CC");
        sofkianoData.setGender("M");
        sofkianoData.setDocument("1152710052");
        sofkianoData.setPictureUrl("/documentos/123");
        sofkianoData.setInformationSofkianos(Arrays.asList());
        sofkianoData.setAssignment(Arrays.asList());


        Mockito.when(mapper.map(any(Sofkiano.class),
                eq(SofkianoData.class))).thenReturn(sofkianoData);
        Mockito.when(sofkianoDataRepository.save(sofkianoData))
                .thenReturn(Mono.just(sofkianoData));
        StepVerifier.create(sofkianoRepositoryAdapter.saveSofkiano(sofkiano))
                .expectNext(sofkiano)
                .verifyComplete();


    }


    @Test
    public void getSofkianoByTypeDocumentAndDocumentTest() {

        Mockito.when(sofkianoDataRepository.findSofkianoDataByTypeDocumentAndDocument(sofkiano.getTypeDocument(),sofkiano.getDocument()))
                .thenReturn(Mono.just(sofkiano));
        StepVerifier.create(sofkianoRepositoryAdapter.findByTypeDocumentAndDocument("CC","1152710052"))
                .expectNext(sofkiano)
                .verifyComplete();
    }


    @Test
    public void findSofkianoByUidTest() {

        Mockito.when(sofkianoDataRepository.findByUid(sofkiano.getUid())).thenReturn(Mono.just(sofkiano));
        StepVerifier.create(sofkianoRepositoryAdapter.findSofkianoByUid("1"))
                .expectNext(sofkiano)
                .verifyComplete();
    }


}