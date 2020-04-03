package co.com.sofka.sofkianos.usecase.sofkianos;

import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static co.com.sofka.sofkianos.domain.commons.UniqueIDGenerator.uuid;

@RequiredArgsConstructor
public class ActionSofkianoUseCase {

    private final SofkianoRepository sofkianoRepository;
    private final ProjectsRepository projectsRepository;
    private final CompaniesRepository companiesRepository;

    public Mono<Sofkiano> saveAssignmentSofkiano(Sofkiano sofkiano) {
        return sofkianoRepository.saveSofkiano(sofkiano);

    }

    public Mono<Sofkiano> saveAssignmentSofkiano(Assignment assignment, String typeDocument, String document, String idCompany) {


        return projectsRepository.findProjectByName(assignment.getProjectName())
                .switchIfEmpty(createProjectFromAssignment(assignment, idCompany))
                .flatMap(setIdProject(assignment))
                .flatMap(addAssignmentToSofkiano(typeDocument, document))
                .flatMap(sofkianoRepository::saveSofkiano);
    }


    public Mono<Sofkiano> saveAssignmentSofkiano(Assignment assignment, String uid,  String idCompany) {


        return projectsRepository.findProjectByName(assignment.getProjectName())
                .switchIfEmpty(createProjectFromAssignment(assignment, idCompany))
                .flatMap(setIdProject(assignment))
                .flatMap(addAssignmentToSofkiano(uid))
                .flatMap(sofkianoRepository::saveSofkiano);
    }

    private Mono<Project> createProjectFromAssignment(Assignment assignment, String idCompany) {

        return uuid()
                .flatMap(id -> Mono.just(Project
                        .builder()
                        .id(id)
                        .idCompany(idCompany)
                        .name(assignment.getProjectName())
                        .service(Arrays.asList(assignment.getService()))
                        .build()))
                .flatMap( project -> companiesRepository
                        .findById(project.getIdCompany())
                        .flatMap( company -> Mono.just(project.toBuilder()
                                .nameCompany(company.getName())
                                .build() )) )
                .flatMap(projectsRepository::saveProject);
    }


    private Function<Project, Mono<? extends Assignment>> setIdProject(Assignment assignment) {
        return project -> Mono.just(assignment.toBuilder().projectId(project.getId()).build());
    }




    private Function<Assignment, Mono<? extends Sofkiano>> addAssignmentToSofkiano(String typeDocument, String document) {
        return assignment1 -> sofkianoRepository.findByTypeDocumentAndDocument(typeDocument,document)
                .flatMap(sofkiano -> {

                    List<Assignment> assignmentList = sofkiano.getAssignment();
                    assignmentList.add(assignment1);

                    return Mono.just(sofkiano.toBuilder().assignment(assignmentList).build());

                });
    }




    private Function<Assignment, Mono<? extends Sofkiano>> addAssignmentToSofkiano(String uid) {
        return assignment1 -> sofkianoRepository.findSofkianoByUid(uid)
                .flatMap(sofkiano -> {

                    List<Assignment> assignmentList = sofkiano.getAssignment();
                    assignmentList.add(assignment1);

                    return Mono.just(sofkiano.toBuilder().assignment(assignmentList).build());

                });
    }



}
