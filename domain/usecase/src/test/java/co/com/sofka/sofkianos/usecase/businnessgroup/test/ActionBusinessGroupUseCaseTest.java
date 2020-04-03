package co.com.sofka.sofkianos.usecase.businnessgroup.test;

import co.com.sofka.sofkianos.domain.businessgroup.BusinessGroup;
import co.com.sofka.sofkianos.domain.businessgroup.gateway.BusinessGroupRepository;
import co.com.sofka.sofkianos.domain.commons.GenericData;
import co.com.sofka.sofkianos.domain.companies.Company;
import co.com.sofka.sofkianos.domain.companies.gateway.CompaniesRepository;
import co.com.sofka.sofkianos.usecase.businessgroup.ActionBusinessGroupUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ActionBusinessGroupUseCaseTest {

    @InjectMocks
    ActionBusinessGroupUseCase underTest;

    @Mock
    BusinessGroupRepository businessGroupRepository;

    @Mock
    CompaniesRepository companiesRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void saveBusinessGroupTest() {

        List<Company> companyList = Arrays.asList(Company.builder().id(GenericData.getAlphaNumericString(10)).build());

        BusinessGroup businessGroup = BusinessGroup.builder()
                .name("John Doe")
                .build();


        Mockito.when(companiesRepository.saveAllCompanies(companyList)).thenReturn(Mono.just(companyList));
        Mockito.when(businessGroupRepository.saveBusinessGroup(businessGroup)).thenReturn(Mono.just(businessGroup));

        StepVerifier.create(underTest.saveBusinessGroupAndCompanies(businessGroup, companyList)).assertNext( businessGroupListTuple2 -> {
                    Assert.assertNotNull(businessGroupListTuple2);
                });
    }
}
