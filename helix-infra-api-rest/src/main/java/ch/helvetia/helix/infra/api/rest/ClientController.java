package ch.helvetia.helix.infra.api.rest;

import ch.helvetia.helix.api.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(ClientController.BASE_URI)
@Api(value = "API to client controller", produces = "application/json")
@RequiredArgsConstructor
public class ClientController {

    protected static final String BASE_URI = "/api/v1/client";

    private final ClientService clientService;

    @ApiOperation(value = "Create a new client", produces = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createClient(UriComponentsBuilder uriComponentsBuilder,
                                   @RequestParam String firstname,
                                   @RequestParam String lastname) {

        Validate.noNullElements(List.of(firstname, lastname),
                "Invalid input parameter/-s: firstname=%s, lastname=%s",
                firstname, lastname);
        Validate.isTrue(firstname.length() > 1, "Firstname's length must be greater than 1");
        Validate.isTrue(lastname.length() > 1, "Lastname's length must be greater than 1");

        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/") //
                .buildAndExpand(clientService.createClient(firstname, lastname));
        return created(uriComponents.toUri()).build();
    }

    @ApiOperation(value = "Add policy to client", produces = "application/json")
    @PostMapping("/{clientId}/policy/{policyId}")
    ResponseEntity<?> addPolicyToClient(@PathVariable UUID clientId,
                                        @PathVariable UUID policyId) {
        Validate.notNull(clientId, "Missing mandatory input parameter: clientId");
        Validate.notNull(policyId, "Missing mandatory input parameter: policyId");
        return ok().body(clientService.addPolicyToClient(clientId, policyId));
    }

    @ApiOperation(value = "Remove policy from client", produces = "application/json")
    @DeleteMapping("/{clientId}/policy/{policyId}")
    ResponseEntity<?> removePolicyFromClient(@PathVariable UUID clientId,
                                             @PathVariable UUID policyId) {
        Validate.notNull(clientId, "Missing mandatory input parameter: clientId");
        Validate.notNull(policyId, "Missing mandatory input parameter: policyId");
        return ok().body(clientService.removePolicyFromClient(clientId, policyId));
    }

    @ApiOperation(value = "Get all client with their insurance policies", produces = "application/json")
    @GetMapping("/")
    ResponseEntity<?> getAllClient() {
        return ok().body(clientService.reliableGetAllClients());
    }

    @ApiOperation(value = "Get client with given id and its respective insurance policy", produces = "application/json")
    @GetMapping("/{clientId}")
    ResponseEntity<?> getClient(@PathVariable UUID clientId) {
        Validate.notNull(clientId, "Missing mandatory input parameter: clientId");
        return ok().body(clientService.getClientById(clientId));
    }

    @ApiOperation(value = "Delete client with given id and its respective insurance policy", produces = "application/json")
    @DeleteMapping("/{clientId}")
    ResponseEntity<?> removeClient(@PathVariable UUID clientId) {
        Validate.notNull(clientId, "Missing mandatory input parameter: clientId");
        clientService.deleteClientWithId(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
