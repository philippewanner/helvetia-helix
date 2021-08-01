package ch.helvetia.helix.api;

import ch.helvetia.helix.core.Client;
import ch.helvetia.helix.core.ClientRepository;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private InsurancePolicyRepository policyRepository;

    private ClientService clientService;

    private final Client client0 = Client.of("firstName0", "lastName0");
    private final Client client1 = Client.of("firstName1", "lastName1");

    @BeforeEach
    void setUp(){
        reset(clientRepository);
        clientService = new ClientService(clientRepository, policyRepository);
    }

    @Test
    void createClient_shouldPersist_whenNotAlreadyExists(){
        // when
        UUID clientId = clientService.createClient("firstName0", "lastName0");

        // then
        assertNotNull(clientId);
        verify(clientRepository, times(1)).save(any());
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    void getAllClients_shouldReturnAllEntities_whenEntitiesExist(){
        // given
        when(clientRepository.findAll()).thenReturn(List.of(client0, client1));

        // when
        List<Client> result = clientService.getAllClients();

        assertTrue(result.contains(client0));
        assertTrue(result.contains(client1));
    }
}
