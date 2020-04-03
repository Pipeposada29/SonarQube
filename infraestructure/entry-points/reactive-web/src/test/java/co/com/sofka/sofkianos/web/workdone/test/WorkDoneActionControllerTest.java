
package co.com.sofka.sofkianos.web.workdone.test;


import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.usecase.parameters.QueryParametersUseCase;
import co.com.sofka.sofkianos.usecase.projects.QueryProjectsUseCase;
import co.com.sofka.sofkianos.usecase.workdone.ActionWorkDoneUseCase;
import co.com.sofka.sofkianos.usecase.workdone.QueryWorkDoneUseCase;
import co.com.sofka.sofkianos.web.workdone.WorkDoneActionController;
import co.com.sofka.sofkianos.web.workdone.WorkDoneQueryController;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@ExtendWith(SpringExtension.class)
public class WorkDoneActionControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private ActionWorkDoneUseCase actionWorkDoneUseCase;


    @Before
    public void setUp () {
        webTestClient = WebTestClient.bindToController(new WorkDoneActionController(actionWorkDoneUseCase)).build();
    }


    @Test
    public void methodDeleteWorkDoneShouldReturnMessageOk(){
            webTestClient.get().uri("/workdone/delete/{id}","id")
                    .accept(MediaType.APPLICATION_JSON).exchange().expectBody();
        }

    @Test
    public void methodRegisterWorkDone(){
         WorkDone workDone = WorkDone.builder()
                 .description("PRUEBA 1")
                 .uid("1111111")
                 .hourlyRate(9)
                 .hours(10)
                 .idProject("1452421")
                 .idSofkiano("121111")
                 .nameProject("Our Sofka")
                 .nameSofkiano("Felipe")
                 .date(new Date()).type("Normal")
                 .build();

         webTestClient.post().uri("workdone/register")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .body(Mono.just(workDone), WorkDone.class)
                 .exchange().expectBody();
     }


    }



