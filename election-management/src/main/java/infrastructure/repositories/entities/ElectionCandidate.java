package infrastructure.repositories.entities;

import domain.candidate.Candidate;
import domain.election.Election;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "election_candidate")
public class ElectionCandidate {
    @EmbeddedId
    private ElectionCandidateId id;
    private Integer votes;

    public ElectionCandidate() {}

    public ElectionCandidate(ElectionCandidateId id, Integer votes){
        this.id = id;
        this.votes = votes;
    }

    public ElectionCandidateId getId() {
        return id;
    }

    public void setId(ElectionCandidateId id) {
        this.id = id;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public static ElectionCandidate fromDomain(Election election, Candidate candidate, Integer votes){
        return new ElectionCandidate(new ElectionCandidateId(election.id(), candidate.id()), votes);
    }
}
