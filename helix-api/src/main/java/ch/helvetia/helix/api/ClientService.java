package ch.helvetia.helix.api;

import ch.helvetia.helix.core.Client;
import ch.helvetia.helix.core.ClientLegacy;
import ch.helvetia.helix.core.ClientRepository;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientLegacy clientLegacy;
    private final InsurancePolicyRepository insurancePolicyRepository;

    @Retryable(
            value = {RuntimeException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 1000))
    public List<Client> reliableGetAllClients() {
        log.info("Retryable method was called.");
        return clientLegacy.getAllClientsRemote();
    }

    @Recover
    public List<Client> getAllClients(RuntimeException e) {
        log.info("Fallback/Recover method was called.");
        return this.getAllClients();
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public UUID createClient(String firstName, String lastNAme) {
        final Client client = Client.of(firstName, lastNAme, Set.of());
        clientRepository.save(client);
        log.info("Client with ID <" + client.getId() + "> created.");
        return client.getId();
    }

    @Transactional
    public void deleteClientWithId(UUID clientId) {
        clientRepository.deleteById(clientId);
        log.info("Client with Id <" + clientId + "> deleted.");
    }

    @Transactional(readOnly = true)
    public Client getClientById(UUID clientId) {
        return clientRepository.findByIdOrThrow(clientId);
    }

    @Transactional
    public UUID addPolicyToClient(UUID clientId, UUID policyId) {
        insurancePolicyRepository.findByIdOrThrow(policyId);
        Client client = clientRepository.findByIdOrThrow(clientId);
        client.getPolicyIds().add(policyId);
        clientRepository.save(client);
        log.info("Insurance policy with ID <" + policyId + "> was added for client with ID <" + clientId + ">.");
        return policyId;
    }

    @Transactional
    public UUID removePolicyFromClient(UUID clientId, UUID policyId) {
        insurancePolicyRepository.findByIdOrThrow(policyId);
        Client client = clientRepository.findByIdOrThrow(clientId);
        client.getPolicyIds().remove(policyId);
        clientRepository.save(client);
        log.info("Insurance policy with ID <" + policyId + "> was removed from client with ID <" + clientId + ">.");
        return policyId;
    }
}
