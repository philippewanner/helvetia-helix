package ch.helvetia.helix.infra.jpa;

import ch.helvetia.helix.core.InsurancePolicy;
import ch.helvetia.helix.core.InsurancePolicyRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaInsurancePolicyRepository extends CrudRepository<InsurancePolicy, UUID>, InsurancePolicyRepository {}
