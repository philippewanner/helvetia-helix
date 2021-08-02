package ch.helvetia.helix.core;

import java.util.List;
import java.util.UUID;

public interface ClientLegacy {

    List<Client> getAllClientsRemote();

    UUID createClient(Client client);
}
