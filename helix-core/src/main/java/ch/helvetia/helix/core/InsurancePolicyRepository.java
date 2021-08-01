package ch.helvetia.helix.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsurancePolicyRepository {

    InsurancePolicy save(InsurancePolicy policy);

    Optional<InsurancePolicy> findById(UUID id);

    default InsurancePolicy findByIdOrThrow(UUID id){
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance policy with id: <"+id+"> not found"));
    }

    void deleteAll();

    List<InsurancePolicy> findAll();

    void deleteById(UUID id);
}
