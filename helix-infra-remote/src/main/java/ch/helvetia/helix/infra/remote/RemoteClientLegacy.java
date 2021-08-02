//package ch.helvetia.helix.infra.remote;
//
//import ch.helvetia.helix.core.Client;
//import ch.helvetia.helix.core.ClientLegacy;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class RemoteClientLegacy implements ClientLegacy {
//
//    private final WebClient webClient;
//    private static final String REMOTE_BASE_URL = "http://localhost:8080";
//
//    public RemoteClientLegacy(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl(REMOTE_BASE_URL).build();
//    }
//
//    @Override
//    public List<Client> getAllClientsRemote() {
//        Mono<Object[]> response = this.webClient.get()
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve().bodyToMono(Object[].class);
//
//        Object[] objects = response.block();
//        if(null == objects){
//            throw new RuntimeException("Objects is null");
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        return Arrays.stream(objects) //
//                .map(o -> mapper.convertValue(o, Client.class)) //
//                .collect(Collectors.toList());
//    }
//}
