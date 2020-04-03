package co.com.sofka.sofkianos.web.workdone;

import co.com.sofka.sofkianos.domain.paramenters.ParametersNames;
import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.usecase.parameters.QueryParametersUseCase;
import co.com.sofka.sofkianos.usecase.projects.QueryProjectsUseCase;
import co.com.sofka.sofkianos.usecase.workdone.QueryWorkDoneUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/workdone", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class WorkDoneQueryController {

    private final QueryParametersUseCase queryParametersUseCase;
    private final QueryProjectsUseCase queryProjectsUseCase;
    private final QueryWorkDoneUseCase queryWorkDoneUseCase;


    @GetMapping(name = "getParameters", path = "/register/getParameters")
    public Mono<ResponseWorkDoneParameters> getRegisterParameters(@RequestParam(name = "dateLong") long date, @RequestHeader(name = "uid") String uid) {

        return Mono.zip(queryParametersUseCase.getParameters(ParametersNames.HOURS_TYPES.getParameterName()), queryParametersUseCase.getParameters(ParametersNames.TYPE_SERVICES.getParameterName()) )
                .map(tuple2 -> ResponseWorkDoneParameters.builder().hoursTypes(tuple2.getT1().getValues()).servicesTypes(tuple2.getT2().getValues()).build() )
                .flatMap(responseWorkDoneParameters -> queryProjectsUseCase.findAssignedProjectToSofkianoAndDate(new Date(date), uid)
                         .map(projects -> ResponseProject.builder().idProject(projects.getId()).name(projects.getName()).build()
                                ).collectList().map(responseProjects -> responseWorkDoneParameters.toBuilder().projects(responseProjects).build())
                        );
    }

    @GetMapping(path = "/{id}")
    public Mono<WorkDone> getByIdWorkDone(@PathVariable("id") String id) {
        return queryWorkDoneUseCase.findByIdWorkDone(id);
    }


    @GetMapping(path = "/getWorkDoneBetweenDates")
    public Mono<ResponseWorkDone> getWorkDoneByDate(@RequestHeader(name = "uid") String uid,
                                                    @RequestParam(name = "startDate") long start,
                                                    @RequestParam(name = "endDate") long end) {

        return queryWorkDoneUseCase.findByIdSofkianoAndDateBetween(uid, new Date(start), new Date(end))
                .flatMap(daysWorkDone -> Mono.just(ResponseDaysWorkDone.builder().date(daysWorkDone.date.getTime()).totalHours(daysWorkDone.totalHours).totalMinutes(daysWorkDone.totalMinutes).workDoneList(daysWorkDone.getWorkDoneList()).build()))
                .collectList()
                .flatMap(responseDaysWorkDones -> Mono.just(ResponseWorkDone.builder().days(responseDaysWorkDones).build()));




    }


}
