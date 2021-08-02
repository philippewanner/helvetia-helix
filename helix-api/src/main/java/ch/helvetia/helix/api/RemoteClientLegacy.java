package ch.helvetia.helix.api;

import ch.helvetia.helix.core.Client;
import ch.helvetia.helix.core.ClientLegacy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RemoteClientLegacy implements ClientLegacy {

    private final WebClient webClient;
    private static final String REMOTE_BASE_URL = "http://localhost:8080/client";

    public RemoteClientLegacy(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder()
                .baseUrl(REMOTE_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<Client> getAllClientsRemote() {
        Mono<Object[]> response = this.webClient.get()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class);

        Object[] objects = response.block();
        if (null == objects) {
            throw new RuntimeException("Objects is null");
        }

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects) //
                .map(o -> mapper.convertValue(o, Client.class)) //
                .collect(Collectors.toList());
    }

    @Override
    public UUID createClient(Client client) {
        return this.webClient.post()
                .uri("/")
                .body(Mono.just(client), Client.class)
                .retrieve()
                .bodyToMono(UUID.class).block();
    }
}
