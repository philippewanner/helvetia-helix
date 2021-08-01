package ch.helvetia.helix.core;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Client {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String firstName;

    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String lastName;
}
