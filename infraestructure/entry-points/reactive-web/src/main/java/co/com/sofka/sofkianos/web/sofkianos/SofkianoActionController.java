package co.com.sofka.sofkianos.web.sofkianos;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.InformationSofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.usecase.sofkianos.ActionSofkianoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


import java.util.Collections;
import java.util.Date;

@CrossOrigin( origins = "*")
@RestController
@RequestMapping(value = "/sofkianos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SofkianoActionController {

    private final ActionSofkianoUseCase actionSofkianoUseCase;




    @PostMapping(path = "/register")
    public Mono<Sofkiano> saveSofkiano(@RequestBody SofkianoRequest sofkianoRequest) {

    return actionSofkianoUseCase.saveAssignmentSofkiano(Sofkiano.builder()
            .document(sofkianoRequest.getDocument())
            .typeDocument(sofkianoRequest.getTypeDocument())
            .uid("")
            .email(sofkianoRequest.getEmail())
            .name(sofkianoRequest.getName())
            .gender(sofkianoRequest.getGender())
            .informationSofkianos(Collections.singletonList(InformationSofkiano.builder()
                    .actualInfo(true)
                    .dataInsertInfo(new Date())
                    .entryCompanyDate(new Date(sofkianoRequest.getEntryCompanyDate()))
                    .position(sofkianoRequest.getPosition())
                    .company(sofkianoRequest.getCompany())
                    .typeOfContract(sofkianoRequest.getTypeOfContract())
                    .salary(sofkianoRequest.getSalary())
                    .flexibleSalary(sofkianoRequest.getFlexibleSalary())
                    .area(sofkianoRequest.getArea())
                    .office(sofkianoRequest.getOffice())
                    .build()))
            .assignment(Collections.emptyList())
            .build());
    }

    @PutMapping(path = "/assignment/{typeDocument}/{document}")
    public Mono<Sofkiano> saveAssignmentSofkiano(@RequestBody AssignmentSofkianoRequets assignmentRequets,
                                                 @PathVariable(name = "typeDocument") String typeDocument,
                                                 @PathVariable(name = "document") String document){



        Assignment assignment = Assignment.builder().assigmentPercent(assignmentRequets.getAssigmentPercent())
                .isBillable(assignmentRequets.getIsBillable())
                .projectName(assignmentRequets.getProjectName())
                .startDate(new Date(assignmentRequets.getStartDate()))
                .releaseDate(assignmentRequets.getReleaseDate() == 0 ? GenericData.GENERIC_DATE : new Date(assignmentRequets.getReleaseDate()))
                .rate(assignmentRequets.getRate())
                .rateType(assignmentRequets.getRateType())
                .service(assignmentRequets.getService()).build();


        return actionSofkianoUseCase.saveAssignmentSofkiano(assignment, typeDocument, document, assignmentRequets.getIdCompany() );

    }

    @PutMapping(path = "/assignment")
    public Mono<Sofkiano> saveAssignmentSofkiano(@RequestBody AssignmentSofkianoRequets assignmentRequets,
                                                 @RequestHeader(name = "UID") String uid){



        Assignment assignment = Assignment.builder().assigmentPercent(assignmentRequets.getAssigmentPercent())
                .isBillable(assignmentRequets.getIsBillable())
                .projectName(assignmentRequets.getProjectName())
                .startDate(new Date(assignmentRequets.getStartDate()))
                .releaseDate(assignmentRequets.getReleaseDate() == 0 ? GenericData.GENERIC_DATE : new Date(assignmentRequets.getReleaseDate()))
                .rate(assignmentRequets.getRate())
                .rateType(assignmentRequets.getRateType())
                .service(assignmentRequets.getService()).build();


        return actionSofkianoUseCase.saveAssignmentSofkiano(assignment, uid, assignmentRequets.getIdCompany() );

    }


}

