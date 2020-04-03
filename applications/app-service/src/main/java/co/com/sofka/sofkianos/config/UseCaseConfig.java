package co.com.sofka.sofkianos.config;

import co.com.sofka.sofkianos.domain.businessgroup.gateway.BusinessGroupRepository;
import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.domain.identity.gateway.IdentityRepository;
import co.com.sofka.sofkianos.domain.paramenters.gateway.ParametersRepository;
import co.com.sofka.sofkianos.domain.projects.ProjectsRepository;
import co.com.sofka.sofkianos.domain.sofkianos.gateway.SofkianoRepository;
import co.com.sofka.sofkianos.domain.workdone.gateway.WorkDoneRepository;
import co.com.sofka.sofkianos.usecase.businessgroup.ActionBusinessGroupUseCase;
import co.com.sofka.sofkianos.usecase.identity.IdentityUseCase;
import co.com.sofka.sofkianos.usecase.parameters.QueryParametersUseCase;
import co.com.sofka.sofkianos.usecase.projects.ActionProjectsUseCase;
import co.com.sofka.sofkianos.usecase.projects.QueryProjectsUseCase;
import co.com.sofka.sofkianos.usecase.sofkianos.ActionSofkianoUseCase;
import co.com.sofka.sofkianos.usecase.sofkianos.QuerySofkianoUseCase;
import co.com.sofka.sofkianos.usecase.workdone.ActionWorkDoneUseCase;
import co.com.sofka.sofkianos.usecase.workdone.QueryWorkDoneUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public QuerySofkianoUseCase createQuerySofkianoUseCase(SofkianoRepository sofkianoRepository) {
        return new QuerySofkianoUseCase(sofkianoRepository);
    }

    @Bean
    public QueryParametersUseCase createQueryParametersUseCase(ParametersRepository parametersRepository) {
        return new QueryParametersUseCase(parametersRepository);
    }
    @Bean
    public  ActionProjectsUseCase actionProjectsUseCase(ProjectsRepository projectsRepository,CompaniesRepository companiesRepository){
        return new ActionProjectsUseCase(projectsRepository, companiesRepository);
    }

    @Bean
    public ActionWorkDoneUseCase actionWorkDoneUseCase(WorkDoneRepository workDoneRepository, SofkianoRepository sofkianoRepository, ProjectsRepository projectsRepository) {
        return new ActionWorkDoneUseCase(workDoneRepository, sofkianoRepository, projectsRepository);
    }

    @Bean
    public QueryWorkDoneUseCase queryWorkDoneUseCase(WorkDoneRepository workDoneRepository, SofkianoRepository sofkianoRepository) {
        return new QueryWorkDoneUseCase(workDoneRepository, sofkianoRepository);
    }

    @Bean
    public QueryProjectsUseCase createQueryProjectsUseCase(SofkianoRepository sofkianoRepository, ProjectsRepository projectsRepository, CompaniesRepository companiesRepository) {
        return new QueryProjectsUseCase(sofkianoRepository, projectsRepository, companiesRepository);
    }
    @Bean
    public ActionSofkianoUseCase actionSofkianoUseCase (SofkianoRepository sofkianoRepository, ProjectsRepository projectsRepository, CompaniesRepository companiesRepository){
        return new ActionSofkianoUseCase(sofkianoRepository, projectsRepository, companiesRepository);
    }
    @Bean
    public ActionBusinessGroupUseCase actionBusinessGroupUseCase(BusinessGroupRepository businessGroupRepository, CompaniesRepository companiesRepository){
        return new ActionBusinessGroupUseCase(businessGroupRepository, companiesRepository);
    }

    @Bean
    public IdentityUseCase identityUseCase(IdentityRepository identityRepository, SofkianoRepository sofkianoRepository, ParametersRepository parametersRepository) {
        return new IdentityUseCase(identityRepository, sofkianoRepository, parametersRepository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }


}
