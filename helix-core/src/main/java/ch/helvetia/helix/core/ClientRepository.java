package ch.helvetia.helix.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(UUID id);

    default Client findByIdOrThrow(UUID id){
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Client with id: <"+id+"> not found"));
    }

    void deleteAll();

    List<Client> findAll();

    void deleteById(UUID id);
}
