package ch.helvetia.helix;

import org.springframework.boot.SpringApplication;
import ch.helvetia.helix.api.ClientService;
import ch.helvetia.helix.api.PolicyService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockRestApplication {

    @MockBean
    public ClientService clientService;

    @MockBean
    public PolicyService policyService;

    public static void main(String[] args){
        SpringApplication.run(MockRestApplication.class, args);
    }
}
