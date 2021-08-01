package ch.helvetia.helix.core;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class InsurancePolicy {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private UUID clientId;

    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String name;

    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Double price;
}
