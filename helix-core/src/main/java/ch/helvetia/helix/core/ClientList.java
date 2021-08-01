package ch.helvetia.helix.core;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class ClientList {

    @Id
    private final UUID id = UUID.randomUUID();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    @Getter(AccessLevel.PRIVATE)
    private final Set<Client> clients = new LinkedHashSet<>();

    public static ClientList of(){
        return new ClientList();
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public int getSize(){
        return this.clients.size();
    }

    public void removeClient(Client client){
        this.clients.remove(client);
    }

    public Client getClient(UUID clientId){
        return this.clients.stream() //
                .filter(c -> c.getId().equals(clientId)) //
                .findFirst() //
                .orElseThrow(() -> new IllegalArgumentException("Item with id: <"+ clientId + "> not found!"));
    }
}
