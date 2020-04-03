package co.com.sofka.sofkianos.mongodb.businessgroup;

import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.businessgroup.gateway.BusinessGroupRepository;
import co.com.sofka.sofkianos.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BusinessGroupRepositoryAdapter extends AdapterOperations<BusinessGroup, BusinessGroupData, String, BusinessGroupDataRepository>
        implements BusinessGroupRepository {

    @Autowired
    public BusinessGroupRepositoryAdapter(BusinessGroupDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, businessGroupData -> mapper.mapBuilder(businessGroupData, BusinessGroup.BusinessGroupBuilder.class).build());
    }

    @Override
    public Flux<BusinessGroup> findAllBusinessGroup() {
        return null;
    }

    @Override
    public Mono<BusinessGroup> saveBusinessGroup(BusinessGroup businessGroup) {
        return save(businessGroup);
    }

    @Override
    public Mono<Company> findByCompanies(String nameCompany) {
        return null;
    }
}
