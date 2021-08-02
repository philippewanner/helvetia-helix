package ch.helvetia.helix.api;

import ch.helvetia.helix.core.Client;
import ch.helvetia.helix.core.ClientRepository;
import ch.helvetia.helix.core.InsurancePolicy;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final InsurancePolicyRepository insurancePolicyRepository;

    @Transactional(readOnly = true)
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Transactional
    public UUID createClient(String firstName, String lastNAme){
        final Client client = Client.of(firstName, lastNAme, Set.of());
        clientRepository.save(client);
        log.info("Client with ID <"+client.getId()+"> created.");
        return client.getId();
    }

    @Transactional
    public void deleteClientWithId(UUID clientId){
        clientRepository.deleteById(clientId);
        log.info("Client with Id <"+clientId+"> deleted.");
    }

    @Transactional(readOnly = true)
    public Client getClientById(UUID clientId){
        return clientRepository.findByIdOrThrow(clientId);
    }

    //@Transactional
    //public UUID addPolicyToClient(UUID clientId, String policyName, Double policyPrice){
    //    InsurancePolicy policy = InsurancePolicy.of(clientId, policyName, policyPrice);
    //    insurancePolicyRepository.save(policy);
    //    log.info("Insurance policy with ID <"+policy.getId()+"> was created for client with ID <"+clientId+">.");
    //    return policy.getId();
    //}
}
