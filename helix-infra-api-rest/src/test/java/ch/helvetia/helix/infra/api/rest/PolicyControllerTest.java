package ch.helvetia.helix.infra.api.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static ch.helvetia.helix.infra.api.rest.PolicyController.BASE_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPolicy() throws Exception {
        String uri = String.format("%s/", BASE_URI);
        mockMvc.perform(post(uri) //
                        .queryParam("name", "MyPolicyName") //
                        .queryParam("price", "25.8") //
                ) //
                .andExpect(status().isCreated());
    }

    @Test
    void getAllPolicies() throws Exception {
        String uri = String.format("%s/", BASE_URI);
        mockMvc.perform(get(uri))
                .andExpect(status().isOk());
    }
}
