package co.com.sofka.sofkianos.mongodb.businessgroup;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessGroupDataRepository extends ReactiveCrudRepository<BusinessGroupData, String>, ReactiveQueryByExampleExecutor<BusinessGroupData> {
}
