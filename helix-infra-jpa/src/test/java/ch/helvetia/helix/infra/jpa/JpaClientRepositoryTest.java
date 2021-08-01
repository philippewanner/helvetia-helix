package ch.helvetia.helix.infra.jpa;

import ch.helvetia.helix.core.Client;
import ch.helvetia.helix.core.ClientRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@DataJpaTest
public class JpaClientRepositoryTest {

    private final Client client0 = Client.of("firstName0", "lastName0");
    private final Client client1 = Client.of("firstName1", "lastName1");

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @AfterEach
    public void afterEach(){
        clientRepository.deleteAll();
    }

    @Test
    public void deleteAll_shouldDeleteAllEntries_whenEntriesExists(){
        // given
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        clientRepository.save(client0);
        clientRepository.save(client1);
        transactionManager.commit(tx);
        assertEquals(clientRepository.findAll().size(), 2);

        // when
        TransactionStatus tx2 = transactionManager.getTransaction(new DefaultTransactionDefinition());
        clientRepository.deleteAll();
        transactionManager.commit(tx2);

        // then
        assertEquals(clientRepository.findAll().size(), 0);
    }

    @Test
    public void save_shouldSaveNewEntity_whenEntityDoesNotAlreadyExist(){
        // given
        assertEquals(clientRepository.findAll().size(), 0);

        // when
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        clientRepository.save(client0);
        transactionManager.commit(tx);

        // then
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 1);
        assertEquals(clients.get(0), client0);
    }

    @Test
    public void deleteById_shouldThrowException_whenClientIdDoesNotExist(){
        // given
        assertEquals(clientRepository.findAll().size(), 0);

        // when
        assertThrows(EmptyResultDataAccessException.class, () -> clientRepository.deleteById(client0.getId()));
    }

    @Test
    public void deleteById_shouldDeleteEntity_whenEntityDoesExist(){
        // given
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        clientRepository.save(client0);
        transactionManager.commit(tx);
        assertEquals(clientRepository.findAll().size(), 1);

        // when
        clientRepository.deleteById(client0.getId());

        // then
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 0);
    }

    @Test
    public void save_shouldOverwriteEntity_whenEntityAlreadyExists(){
        // given
        TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
        clientRepository.save(client0);
        transactionManager.commit(tx);
        assertEquals(clientRepository.findAll().size(), 1);

        // when
        TransactionStatus tx2 = transactionManager.getTransaction(new DefaultTransactionDefinition());
        clientRepository.save(client0);
        transactionManager.commit(tx2);

        // then
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 1);
        assertEquals(clients.get(0), client0);
    }
}
