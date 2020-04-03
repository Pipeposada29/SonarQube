package co.com.sofka.sofkianos.config;

import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.businessgroup.gateway.BusinessGroupRepository;
import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.domain.identity.IdentityInfo;
import co.com.sofka.sofkianos.domain.identity.gateway.IdentityRepository;
import co.com.sofka.sofkianos.domain.paramenters.Parameter;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import co.com.sofka.sofkianos.domain.projects.Project;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import co.com.sofka.sofkianos.domain.sofkianos.Assignment;
import co.com.sofka.sofkianos.domain.sofkianos.Sofkiano;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import co.com.sofka.sofkianos.domain.workdone.WorkDone;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

@Log
@Configuration
public class DefaultBeansConfig {

    @Bean
    @ConditionalOnMissingBean
    public SofkianoRepository sofkianoRepository() {
        alertFakeBean("SofkianoRepository");
        return sofkianoRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public ParametersRepository parametersRepository() {
        alertFakeBean("ParametersRepository");
        return parametersRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public WorkDoneRepository workDoneRepository() {
        alertFakeBean("WorkDoneRepository");
        return workDoneRepository;
    }


    @Bean
    @ConditionalOnMissingBean
    public ProjectsRepository projectsRepository() {
        alertFakeBean("ProjectsRepository");
        return projectsRepository;
    }


    @Bean
    @ConditionalOnMissingBean
    public BusinessGroupRepository businessGroupRepository(){
        alertFakeBean("BusinessGroupRepository");
        return businessGroupRepository;
    }


    @Bean
    @ConditionalOnMissingBean
    public IdentityRepository identityRepository() {
        alertFakeBean("IdentityRepository");
        return identityRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public CompaniesRepository companiesRepository() {
        alertFakeBean("CompaniesRepository");
        return companiesRepository;
    }


    private final BusinessGroupRepository businessGroupRepository = new BusinessGroupRepository() {
        @Override
        public Flux<BusinessGroup> findAllBusinessGroup() {
            return null;
        }

        @Override
        public Mono<BusinessGroup> saveBusinessGroup(BusinessGroup businessGroup) {
            log.info("Guardado a repo sin implementación: ");
            log.info(businessGroup.toString());
            return Mono.empty();
        }

        @Override
        public Mono<Company> findByCompanies(String nameCompany) {
            return Mono.empty();
        }
    };

    private final WorkDoneRepository workDoneRepository = new WorkDoneRepository() {

        @Override
        public Mono<WorkDone> save(WorkDone workDone) {
            return Mono.empty();
        }

        @Override
        public Mono<WorkDone> findByIdWorkDone(String id) {
            return Mono.just(WorkDone.builder().build());
        }


        @Override
        public Mono<String> deleteById(String id) {
            return Mono.just("FAKE");
        }

        @Override
        public Flux<WorkDone> findByIdSofkianoAndDateBetween(String idSofkiano, Date startDate, Date endDate) {
            return workDoneRepository.findByIdSofkianoAndDateBetween(idSofkiano, startDate, endDate);
        }

        @Override
        public Flux<WorkDone> findByIdSofianoAndDate(String idSofkiano, Date date) {
            return null;
        }
    };

    private final ParametersRepository parametersRepository = new ParametersRepository() {
        @Override
        public Mono<Parameter> findParameterByName(String nameParameter) {
            return Mono.just(Parameter.builder().name(nameParameter).values(Arrays.asList("EXTRASF", "NORMALESF")).build());
        }
    };


    private final SofkianoRepository sofkianoRepository = new SofkianoRepository() {
        @Override
        public Mono<Sofkiano> findSofkianoById(String id) {
            return Mono.just(Sofkiano.builder().build());
        }

        @Override
        public Flux<Sofkiano> findAllSofkianos() {
            return Flux.just(Sofkiano.builder().build());
        }

        @Override
        public Mono<Sofkiano> saveSofkiano(Sofkiano sofkiano) {

            return Mono.just(Sofkiano.builder().build());
        }

        @Override
        public Mono<Sofkiano> findByTypeDocumentAndDocument(String typeDocument, String document) {
            return Mono.just(Sofkiano.builder().build());
        }

        @Override
        public Mono<Sofkiano> saveAssignmentSofkiano(Assignment assignment) {
            return Mono.just(Sofkiano.builder().build());
        }

        @Override
        public Mono<Sofkiano> findSofkianoByUid(String uid) {
            return Mono.just(Sofkiano.builder().build());
        }

        @Override
        public Mono<Sofkiano> findSofkianoByEmail(String email) {
            return Mono.just(Sofkiano.builder().build());
        }

    };


    private final ProjectsRepository projectsRepository = new ProjectsRepository() {

        @Override
        public Flux<Project> findAllProjects() {
            return Flux.just(Project.builder().build());
        }

        @Override
        public Mono<Project> findByIdProjects(String idProject) {
            return Mono.just(Project.builder().build());
        }

        @Override
        public Mono<Project> saveProject(Project project) {
            return Mono.just(Project.builder().build());
        }

        @Override
        public Mono<Project> findProjectByName(String projectName) {
            return Mono.empty();
        }

        @Override
        public Flux<Project> findByIdCompany(String idCompany) {
            return Flux.empty();
        }
    };


    private final IdentityRepository identityRepository = new IdentityRepository() {
        @Override
        public Mono<IdentityInfo> verifyTokenId(String tokenId) {
            return Mono.just(IdentityInfo.builder().build());
        }
    };

    private final CompaniesRepository companiesRepository = new CompaniesRepository() {


        @Override
        public Mono<List<Company>> saveAllCompanies(List<Company> list) {
            return  Mono.empty();
        }

        @Override
        public Mono<Company> findByName(String nameCompany) {
            return Mono.empty();
        }

        @Override
        public Flux<Company> findAll() {
            return Flux.empty();
        }

        @Override
        public Mono<Company> findById(String id) {
            return Mono.empty();
        }
    };


    private void alertFakeBean(String beanName) {
        log.log(Level.WARNING, "CONFIGURACION FAKE: " + beanName, beanName);
    }


}
