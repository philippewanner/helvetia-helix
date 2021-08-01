package ch.helvetia.helix.infra.jpa;

import ch.helvetia.helix.core.Client;
import ch.helvetia.helix.core.ClientRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaClientRepository extends CrudRepository<Client, UUID>, ClientRepository {}
