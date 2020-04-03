package co.com.sofka.sofkianos.web.sofkiano.test;

import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.usecase.parameters.QueryParametersUseCase;
import co.com.sofka.sofkianos.usecase.projects.QueryProjectsUseCase;
import co.com.sofka.sofkianos.usecase.sofkianos.QuerySofkianoUseCase;
import co.com.sofka.sofkianos.usecase.workdone.QueryWorkDoneUseCase;
import co.com.sofka.sofkianos.web.sofkianos.SofkianoQueryController;
import co.com.sofka.sofkianos.web.workdone.WorkDoneQueryController;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@ExtendWith(SpringExtension.class)
public class SofkianoQueryControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private QueryParametersUseCase queryParametersUseCase;
    @MockBean
    private QueryProjectsUseCase queryProjectsUseCase;
    @MockBean
    private QuerySofkianoUseCase querySofkianoUseCase;
    @MockBean
    private QueryWorkDoneUseCase queryWorkDoneUseCase;


    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToController(new WorkDoneQueryController(queryParametersUseCase, queryProjectsUseCase, queryWorkDoneUseCase )).build();
    }

    @Before
    public void setUpTwo() {
        webTestClient = WebTestClient.bindToController(new SofkianoQueryController(querySofkianoUseCase)).build();

    }


    @Test
    public void shouldGetSofkianoById() {
        webTestClient.get().uri("/sofkiano/{id}","12345").accept(MediaType.APPLICATION_JSON)
                .exchange().expectBody(Sofkiano.class);
    }

    @Test
    public void methodgetSofkianoByTypeDocumentAndDocument(){
        webTestClient.get().uri("/sofkiano/{typeDocument}/{document}","CC","1152710052")
                .accept(MediaType.APPLICATION_JSON).exchange().expectBody(Sofkiano.class);
    }



}
