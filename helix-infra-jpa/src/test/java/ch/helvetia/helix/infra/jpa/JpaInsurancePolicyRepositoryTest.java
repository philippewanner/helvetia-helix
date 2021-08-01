package ch.helvetia.helix.infra.jpa;

import ch.helvetia.helix.core.InsurancePolicy;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@DataJpaTest
public class JpaInsurancePolicyRepositoryTest {

    private final InsurancePolicy policy0 = InsurancePolicy.of(UUID.randomUUID(), "policy0", 10.0);
    private final InsurancePolicy policy1 = InsurancePolicy.of(UUID.randomUUID(), "policy1", 230.0);

    @Autowired
    private InsurancePolicyRepository policyRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @AfterEach
    public void afterEach(){
        policyRepository.deleteAll();
    }

    @Test
    public void deleteAll_shouldDeleteAllEntries_whenEntriesExists(){
        // given
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        policyRepository.save(policy0);
        policyRepository.save(policy1);
        transactionManager.commit(tx);
        assertEquals(policyRepository.findAll().size(), 2);

        // when
        TransactionStatus tx2 = transactionManager.getTransaction(new DefaultTransactionDefinition());
        policyRepository.deleteAll();
        transactionManager.commit(tx2);

        // then
        assertEquals(policyRepository.findAll().size(), 0);
    }

    @Test
    public void save_shouldSaveNewEntity_whenEntityDoesNotAlreadyExist(){
        // given
        assertEquals(policyRepository.findAll().size(), 0);

        // when
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        policyRepository.save(policy0);
        transactionManager.commit(tx);

        // then
        List<InsurancePolicy> policies = policyRepository.findAll();
        assertEquals(policies.size(), 1);
        assertEquals(policies.get(0), policy0);
    }

    @Test
    public void deleteById_shouldThrowException_whenPolicyIdDoesNotExist(){
        // given
        assertEquals(policyRepository.findAll().size(), 0);

        // when
        assertThrows(EmptyResultDataAccessException.class, () -> policyRepository.deleteById(policy0.getId()));
    }

    @Test
    public void deleteById_shouldDeleteEntity_whenEntityDoesExist(){
        // given
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        policyRepository.save(policy0);
        transactionManager.commit(tx);
        assertEquals(policyRepository.findAll().size(), 1);

        // when
        policyRepository.deleteById(policy0.getId());

        // then
        List<InsurancePolicy> policies = policyRepository.findAll();
        assertEquals(policies.size(), 0);
    }

    @Test
    public void save_shouldOverwriteEntity_whenEntityAlreadyExists(){
        // given
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        policyRepository.save(policy0);
        transactionManager.commit(tx);
        assertEquals(policyRepository.findAll().size(), 1);

        // when
        TransactionStatus tx2 = transactionManager.getTransaction(new DefaultTransactionDefinition());
        policyRepository.save(policy0);
        transactionManager.commit(tx2);

        // then
        List<InsurancePolicy> policies = policyRepository.findAll();
        assertEquals(policies.size(), 1);
        assertEquals(policies.get(0), policy0);
    }
}
