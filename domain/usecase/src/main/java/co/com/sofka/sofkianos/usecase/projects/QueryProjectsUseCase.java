package co.com.sofka.sofkianos.usecase.projects;

import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class QueryProjectsUseCase {

    private final SofkianoRepository sofkianoRepository;
    private final ProjectsRepository projectsRepository;
    private final CompaniesRepository companiesRepository;


    public Flux<Project> findAssignedProjectToSofkianoAndDate(Date date, String uid) {
        return sofkianoRepository.findSofkianoByUid(uid)
                .flatMapIterable(getAssignmentsByDate(date))
                .flatMap(assignment -> projectsRepository.findByIdProjects(assignment.getProjectId()));
    }

    private Function<Sofkiano, Iterable<? extends Assignment>> getAssignmentsByDate(Date date) {
        return sofkiano -> sofkiano.getAssignment()
                .stream()
                .filter(assignment -> isAssigmentDate(date, assignment.getStartDate(), assignment.getReleaseDate()))
                .collect(toList());
    }

    private static boolean isAssigmentDate(Date date, Date startDate, Date releaseDate) {
        return date.compareTo(startDate) >= 0
                && (date.compareTo(releaseDate) <= 0
                || releaseDate.equals(GenericData.GENERIC_DATE));
    }


    public Flux<Project> findAllProjects() {
        return projectsRepository.findAllProjects();
    }


    public Mono<Project> findByIdProject(String idProject) {
        return projectsRepository.findByIdProjects(idProject);
    }

    public Flux<Tuple2<List<String>, Company>> findAllCompanies() {
        return companiesRepository.findAll()
                .flatMap(company -> getCompanyAndListProjects(company));
    }

    private Publisher<? extends Tuple2<List<String>, Company>> getCompanyAndListProjects(Company company) {
        return projectsRepository.findByIdCompany(company.getId())
                .map(project -> project.getName())
                .collectList()
                .zipWith(Mono.just(company));
    }
}

/*

   Mono<List<String>> listProjects = projectsRepository
                            .findByIdCompany(company.getId())
                            .map(project -> project.getName())
                            .collectList();

 */