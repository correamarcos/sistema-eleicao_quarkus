package infrastructure.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "elections")
public class Election {
    @Id
    private String id;

    public Election() {}

    public Election(String id){ this.id = id; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Election fromDomain(domain.election.Election domain){
        return new Election(domain.id());
    }
}
