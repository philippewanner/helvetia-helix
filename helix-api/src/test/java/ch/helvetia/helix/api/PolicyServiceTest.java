package ch.helvetia.helix.api;

import ch.helvetia.helix.core.InsurancePolicy;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {

    @Mock
    private InsurancePolicyRepository policyRepository;

    private PolicyService policyService;

    private final InsurancePolicy policy0 = InsurancePolicy.of("name0", 2.0);
    private final InsurancePolicy policy1 = InsurancePolicy.of("name1", 37.0);

    @BeforeEach
    void setUp(){
        reset(policyRepository);
        policyService = new PolicyService(policyRepository);
    }

    @Test
    void createPolicy_shouldPersist_whenNotAlreadyExists(){
        // when
        UUID clientId = policyService.createPolicy("name", 10.0);

        // then
        assertNotNull(clientId);
        verify(policyRepository, times(1)).save(any());
        verifyNoMoreInteractions(policyRepository);
    }

    @Test
    void getAllPolicies_shouldReturnAllEntities_whenEntitiesExist(){
        // given
        when(policyRepository.findAll()).thenReturn(List.of(policy0, policy1));

        // when
        List<InsurancePolicy> result = policyService.getAllPolicies();

        assertTrue(result.contains(policy0));
        assertTrue(result.contains(policy1));
    }
}
