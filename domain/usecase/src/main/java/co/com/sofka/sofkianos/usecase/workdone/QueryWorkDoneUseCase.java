package co.com.sofka.sofkianos.usecase.workdone;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import co.com.sofka.sofkianos.domain.workdone.DaysWorkDone;
import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Function;

@AllArgsConstructor
public class QueryWorkDoneUseCase {


    private final WorkDoneRepository workDoneRepository;
    private final SofkianoRepository sofkianoRepository;

    public Mono<WorkDone> findByIdWorkDone(String id) {
        return workDoneRepository.findByIdWorkDone(id);
    }

    public Flux<DaysWorkDone> findByIdSofkianoAndDateBetween(String uid, Date startDate, Date endDate) {


       return sofkianoRepository.findSofkianoByUid(uid)
                .flatMapMany(sofkiano -> workDoneRepository.findByIdSofkianoAndDateBetween(sofkiano.getId(), aDayBefore(startDate), aDayAfter(endDate)))
                .groupBy(WorkDone::getDate)
               .sort(Comparator.comparing(dateWorkDoneGroupedFlux -> dateWorkDoneGroupedFlux.key()))
                .flatMap(getGroupedFluxPublisherFunction());


    }

    private Function<GroupedFlux<Date, WorkDone>, Publisher<? extends DaysWorkDone>> getGroupedFluxPublisherFunction() {
        return dateWorkDoneGroupedFlux -> dateWorkDoneGroupedFlux
                    .collectList()
                    .map(workDones -> {

                        int totalHorus = workDones
                                .stream()
                                .map(WorkDone::getHours)
                                .reduce(0, Integer::sum);
                        int totalMinutes = workDones
                                .stream()
                                .map(WorkDone::getMinutes)
                                .reduce(0, Integer::sum);

                        if (totalMinutes >= 60){
                            totalHorus = totalHorus+(totalMinutes/60);
                            totalMinutes =0 + (totalMinutes % 60);
                        }


                        return  DaysWorkDone
                                .builder()
                                .workDoneList(workDones)
                                .date(dateWorkDoneGroupedFlux.key())
                                .totalHours(totalHorus)
                                .totalMinutes(totalMinutes)
                                .build();

                    });

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
