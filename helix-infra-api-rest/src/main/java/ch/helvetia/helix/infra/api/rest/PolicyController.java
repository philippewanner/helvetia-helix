package ch.helvetia.helix.infra.api.rest;

import ch.helvetia.helix.api.PolicyService;
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
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(PolicyController.BASE_URI)
@Api(value = "API to policy controller", produces = "application/json")
@RequiredArgsConstructor
public class PolicyController {

    protected static final String BASE_URI = "/api/v1/policy";

    private final PolicyService policyService;

    @ApiOperation(value = "Create a new policy", produces = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createPolicy(
            UriComponentsBuilder uriComponentsBuilder,
            @RequestParam String name,
            @RequestParam Double price) {

        Validate.noNullElements(Arrays.asList(name, price),
                "Invalid input parameter/-s: policyName=%s, price=%f",
                name, price);

        Validate.isTrue(price >= 1.0, "The price has to be greater or equal to 1");

        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/")
                .buildAndExpand(policyService.createPolicy(name, price)
                );

        return created(uriComponents.toUri()).build();
    }

    @ApiOperation(value = "Get all policies", produces = "application/json")
    @GetMapping("/")
    ResponseEntity<?> getAllPolicies() {
        return ok().body(policyService.getAllPolicies());
    }

    @ApiOperation(value = "Get policy with given id", produces = "application/json")
    @GetMapping("/{clientId}/insurance-policy/{policyId}")
    ResponseEntity<?> getPolicy(@PathVariable UUID clientId, //
                                @PathVariable UUID policyId) {
        Validate.notNull(policyId, "Missing mandatory input parameter: policyId");
        return ok().body(policyService.getPolicyById(policyId));
    }

    @ApiOperation(value = "Delete policy with given id", produces = "application/json")
    @DeleteMapping("/{clientId}/insurance-policy/{policyId}")
    ResponseEntity<?> removePolicy(@PathVariable UUID policyId) {
        Validate.notNull(policyId, "Missing mandatory input parameter: policyId");
        policyService.deletePolicyWithId(policyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
