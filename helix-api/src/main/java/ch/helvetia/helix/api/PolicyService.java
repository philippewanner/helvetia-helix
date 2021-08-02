package ch.helvetia.helix.api;

import ch.helvetia.helix.core.InsurancePolicy;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    @Transactional
    public UUID createPolicy(String name, Double price){
        final InsurancePolicy policy = InsurancePolicy.of(name, price);
        insurancePolicyRepository.save(policy);
        log.info("Policy with ID <"+policy.getId()+"> created.");
        return policy.getId();
    }

    @Transactional
    public void deletePolicyWithId(UUID policyId) {
        insurancePolicyRepository.deleteById(policyId);
        log.info("Policy with Id <"+policyId+"> deleted.");
    }

    @Transactional(readOnly = true)
    public List<InsurancePolicy> getAllPolicies(){
        return insurancePolicyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public InsurancePolicy getPolicyById(UUID policyId){
        return insurancePolicyRepository.findByIdOrThrow(policyId);
    }
}
