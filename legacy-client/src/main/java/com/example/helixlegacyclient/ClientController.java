package com.example.helixlegacyclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping(ClientController.BASE_URI)
public class ClientController {

    protected static final String BASE_URI = "/client";
    private final List<Client> clientCache = new ArrayList<>();

    {
        clientCache.add(Client.of("Marcel", "Lupin", Set.of()));
        clientCache.add(Client.of("John", "Doe", Set.of()));
    }

    @GetMapping("/")
    ResponseEntity<?> getAllClient() {
        log.info("Legacy system - Clients retrieved");
        return ok().body(clientCache);
    }

    @PostMapping("/")
    ResponseEntity<?> createClient(UriComponentsBuilder uriComponentsBuilder,
                                   @RequestBody Client client) {
        log.info("Legacy system - Client created");
        clientCache.add(client);
        UriComponents uriComponents = uriComponentsBuilder
                .buildAndExpand(client.getId());
        return created(uriComponents.toUri()).build();
    }
}
