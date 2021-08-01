package ch.helvetia.helix.infra.api.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static ch.helvetia.helix.infra.api.rest.ClientController.BASE_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    private static final UUID MOCK_CLIENT_ID = UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createClient() throws Exception {
        String uri = String.format("%s/", BASE_URI);
        mockMvc.perform(post(uri) //
                        .queryParam("firstname", "firstnameA") //
                        .queryParam("lastname", "lastnameA") //
                ) //
                .andExpect(status().isCreated());
    }

    @Test
    void addInsurancePolicyToClient() throws Exception {
        String uri = String.format("%s/%s/insurance-policy/", BASE_URI, MOCK_CLIENT_ID);
        mockMvc.perform(post(uri) //
                        .queryParam("policyName", "policyA") //
                        .queryParam("price", "300") //
                ) //
                .andExpect(status().isCreated());
    }

    @Test
    void getAllClients() throws Exception {
        String uri = String.format("%s/", BASE_URI);
        mockMvc.perform(get(uri))
                .andExpect(status().isOk());
    }
}
