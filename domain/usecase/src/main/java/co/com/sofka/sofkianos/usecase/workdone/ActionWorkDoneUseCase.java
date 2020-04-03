package co.com.sofka.sofkianos.usecase.workdone;

import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.geom.Arc2D;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public class ActionWorkDoneUseCase {

    private final WorkDoneRepository workDoneRepository;
    private final SofkianoRepository sofkianoRepository;
    private final ProjectsRepository projectsRepository;

    public Mono<WorkDone> registerWorkDone(WorkDone workDone, String uid) {

        return workDone.getHours() < 0 || workDone.getMinutes() <30 && workDone.getHours()<=0 ? Mono.error(new Exception("Debes ingresar minimo 30 minutos"))
                : sofkianoRepository.findSofkianoByUid(uid)
                .flatMap(sumHoursAndMinutesAndValidatorPerDay(workDone))
                .flatMap(sofkiano -> Mono.just(workDone.toBuilder()
                        .nameSofkiano(sofkiano.getName())
                        .idSofkiano(sofkiano.getId())
                        .uid(sofkiano.getUid())
                        .build()))
                .flatMap(findProjectByIdAndSaveWorkDone());
    }

    private Function<Sofkiano, Mono<? extends Sofkiano>> sumHoursAndMinutesAndValidatorPerDay(WorkDone workDone) {
        return sofkiano -> sumWorkDoneHoursInDate(sofkiano.getId(), workDone.getDate())
                .flatMap(hours -> sumWorkDoneMinutesIsDate(sofkiano.getId(),workDone.getDate())
                        .map(integer -> workDone.getMinutes() + integer)
                        .flatMap(minutes -> sumHoursYMinutes(hours,minutes)
                .flatMap(integer -> integer + workDone.getHours() >=24 && minutes >=0
                        ? Mono.error(new Exception("Ha sobrepasado la cantidad de horas máximas por día (24h)"))
                        : Mono.just(sofkiano))));
    }

    private Function<WorkDone, Mono<? extends WorkDone>> findProjectByIdAndSaveWorkDone() {
        return workDone1 -> projectsRepository
                .findByIdProjects(workDone1.getIdProject())
                .switchIfEmpty(Mono.error(new Exception("El proyecto no registra en nuestra base de datos")))
                .map(projects -> workDone1.toBuilder()
                        .nameProject(projects.getName())
                        .build())
                .flatMap(workDoneRepository::save);
    }

    public Mono<String> deleteById(String id) {
        return workDoneRepository.deleteById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::isNull)
                .flatMap(workDoneToBeDeleted -> workDoneRepository.deleteById(id))
                .then(Mono.just(id));
    }

    private Date dateWithoutTime(Date date) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        return dateCalendar.getTime();
    }

    private Mono<Integer> sumWorkDoneHoursInDate(String id, Date date) {
        return workDoneRepository.findByIdSofianoAndDate(id, date).flatMap(workDone -> Mono.just(workDone.getHours())).reduce(0,Integer::sum);
    }

    private Mono<Integer> sumWorkDoneMinutesIsDate(String id, Date date) {
        return workDoneRepository.findByIdSofianoAndDate(id, date).flatMap(workDone -> Mono.just(workDone.getMinutes())).reduce(0, Integer::sum);
    }

    private Mono<Integer> sumHoursYMinutes(Integer hours, Integer minutes){

        if (minutes >= 60) {
        hours = hours + (minutes / 60);
    }
        return Mono.just(hours);
    }


    private Date aDayBefore(Date date) {
        return addOrSubtractDaysToADate(date, -1);
    }

    private Date aDayAfter(Date date) {
        return addOrSubtractDaysToADate(date, 1);
    }

    private Date addOrSubtractDaysToADate(Date date, int days) {
        Calendar aDate = Calendar.getInstance();

        aDate.setTime(date);
        aDate.add(Calendar.DATE, days);

        return aDate.getTime();
    }
}
