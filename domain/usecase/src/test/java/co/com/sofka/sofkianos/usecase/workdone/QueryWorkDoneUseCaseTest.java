package co.com.sofka.sofkianos.usecase.workdone;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RunWith(MockitoJUnitRunner.Silent.class)
public class QueryWorkDoneUseCaseTest {

    @Mock
    private WorkDoneRepository workDoneRepository;

    @Mock
    private SofkianoRepository sofkianoRepository;

    @InjectMocks
    private QueryWorkDoneUseCase underTest;

    @Test
    public void findByIdSofkianoAndDateBetween() {

        List<WorkDone> workDoneList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {


            workDoneList.add(createRandomWorkDone());
        }

        String idSofkiano = GenericData.getAlphaNumericString(10);
        String uid = GenericData.getAlphaNumericString(10);
        Date startDate = new Date();
        Date endDate= new Date();

        Mockito.when(sofkianoRepository.findSofkianoByUid(uid)).thenReturn(Mono.just(Sofkiano.builder().id(idSofkiano).build()));

        Mockito.when(workDoneRepository.findByIdSofkianoAndDateBetween(uid, startDate, endDate)).thenReturn(Flux.fromIterable(workDoneList));


        StepVerifier.create( underTest.findByIdSofkianoAndDateBetween(uid, startDate, endDate)).assertNext(daysWorkDone -> {

            Assert.assertNotNull(daysWorkDone);
            Assert.assertFalse(daysWorkDone.workDoneList.isEmpty());
            Assert.assertTrue(daysWorkDone.totalHours > 0);
        });

        Mockito.verify(sofkianoRepository, Mockito.times(1)).findSofkianoByUid(uid);
        //Mockito.verify(workDoneRepository, Mockito.times(1)).findByIdSofkianoAndDateBetween(idSofkiano, startDate, endDate);



    }

    private WorkDone createRandomWorkDone() {

        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Date oneYearsAgo = new Date(now - aDay * 365);
        Date tenDaysAgo = new Date(now - aDay * 10);

        return WorkDone.builder().description(GenericData.getAlphaNumericString(20)).date(GenericData.randomDateBetween(oneYearsAgo, tenDaysAgo)).build();
    }



    @Test
    public void findByIdWorkDone() {

        String idWorkDone = GenericData.getAlphaNumericString(10);

        Mockito.when(workDoneRepository.findByIdWorkDone(idWorkDone)).thenReturn(Mono.just(WorkDone.builder().id(idWorkDone).build()));

        StepVerifier.create( underTest.findByIdWorkDone(idWorkDone)).assertNext( workDone -> {

            Assert.assertNotNull(workDone);
            Assert.assertTrue(workDone.getId().equals(idWorkDone));
        });

        Mockito.verify(workDoneRepository, Mockito.times(1)).findByIdWorkDone(idWorkDone);
    }
}