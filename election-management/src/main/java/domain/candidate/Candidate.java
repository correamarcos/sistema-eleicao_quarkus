package domain.candidate;

import java.util.Optional;
import java.util.UUID;

public record Candidate(String id,
                        Optional<String> photo,
                        String givenName,
                        String familyName,
                        String email,
                        Optional<String> phone,
                        Optional<String> jobTitle) {
    public static Candidate create(Optional<String> id,
                                   Optional<String> photo,
                                   String givenName,
                                   String familyName,
                                   String email,
                                   Optional<String> phone,
                                   Optional<String> jobTitle){

        return new Candidate(id.orElse(UUID.randomUUID().toString()), photo, givenName, familyName, email, phone, jobTitle);
    }
}
