package com.example.helixlegacyclient;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Client {

    private final UUID id = UUID.randomUUID();

    @Setter(AccessLevel.PRIVATE)
    private String firstName;

    @Setter(AccessLevel.PRIVATE)
    private String lastName;

    @Setter(AccessLevel.PRIVATE)
    private Set<UUID> policyIds = new HashSet<>();
}
