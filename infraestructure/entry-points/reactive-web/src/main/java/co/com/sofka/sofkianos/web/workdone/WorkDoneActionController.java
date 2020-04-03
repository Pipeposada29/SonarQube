package co.com.sofka.sofkianos.web.workdone;

import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.usecase.workdone.ActionWorkDoneUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@CrossOrigin( origins = "*")
@RestController
@RequestMapping(value = "/workdone", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class WorkDoneActionController {

    private final ActionWorkDoneUseCase actionWorkDoneUseCase;

    @PostMapping(path = "/register")
    public Mono<ResponseMessageWorkDone> postRegisterWorkDone(@RequestBody RequestWorkDone workDone, @RequestHeader(name = "uid") String uid) {
        return actionWorkDoneUseCase
                .registerWorkDone(workDone.getWorkDone(), uid)
                .map(workDoneReturn -> ResponseMessageWorkDone.builder()
                        .function("/workdone/register")
                        .message("Ok - " + workDoneReturn.getDescription())
                        .build());
    }

      @DeleteMapping(path = "/delete/{id}")
      public Mono<ResponseMessageWorkDone> deleteWorkDone(@PathVariable("id") String id) {

          return actionWorkDoneUseCase.deleteById(id)
                  .map(s -> ResponseMessageWorkDone.builder().function("delete/".concat(id)).message("Ok").code(0).build());


      }


}
