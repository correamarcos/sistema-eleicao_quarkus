package api.dto.in;

import java.util.Optional;

public record Candidate(Optional<String> id,
                        Optional<String> photo,
                        String givenName,
                        String familyName,
                        String email,
                        Optional<String> phone,
                        Optional<String> jobTitle) {

    public domain.candidate.Candidate toDomain(){
        return domain.candidate.Candidate.create(id, photo, givenName, familyName, email, phone, jobTitle);
    }

    public domain.candidate.Candidate toDomain(String idCandidate){
        return domain.candidate.Candidate.create(Optional.of(idCandidate), photo, givenName, familyName, email, phone, jobTitle);
    }
}
