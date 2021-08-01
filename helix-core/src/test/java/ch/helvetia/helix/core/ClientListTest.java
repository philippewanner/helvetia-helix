package ch.helvetia.helix.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientListTest {

    private final Client client0 = Client.of("firstName0", "lastName0");
    private final Client client1 = Client.of("firstName1", "lastName1");

    @Test
    void addClient_shouldAddClient_whenClientIsNonNull(){
        // given
        ClientList list = ClientList.of();

        // when
        list.addClient(client0);
        list.addClient(client1);

        // then
        assertEquals(list.getSize(), 2);
        assertEquals(list.getClient(client0.getId()), client0);
        assertEquals(list.getClient(client1.getId()), client1);
    }
}
