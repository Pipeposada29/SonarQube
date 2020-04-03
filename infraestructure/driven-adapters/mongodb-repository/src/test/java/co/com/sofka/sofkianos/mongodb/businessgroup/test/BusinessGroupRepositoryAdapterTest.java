package co.com.sofka.sofkianos.mongodb.businessgroup.test;

import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.mongodb.businessgroup.BusinessGroupData;
import co.com.sofka.sofkianos.mongodb.businessgroup.BusinessGroupDataRepository;
import co.com.sofka.sofkianos.mongodb.businessgroup.BusinessGroupRepositoryAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusinessGroupRepositoryAdapterTest {
    @InjectMocks
    BusinessGroupRepositoryAdapter businessGroupRepositoryAdapter;

    @Mock
    BusinessGroupDataRepository businessGroupDataRepository;

    @Mock
    private ObjectMapper mapper;

    @Test
    public void saveBusinessGroupTest() {
        BusinessGroup businessGroup = BusinessGroup.builder()
                .name("John Doe")
                .build();

        BusinessGroupData businessGroupData = new BusinessGroupData();

        businessGroupData.setId("1");
        businessGroupData.setName("John Doe");
        businessGroupData.setCompanies(List.of());

        when(mapper.map(any(BusinessGroup.class),
                eq(BusinessGroupData.class))).thenReturn(businessGroupData);

        Mockito.when(businessGroupDataRepository.save(businessGroupData)).thenReturn(Mono.just(businessGroupData));

        StepVerifier.create(businessGroupRepositoryAdapter.saveBusinessGroup(businessGroup))
                .expectNext(businessGroup)
                .verifyComplete();
    }
}
