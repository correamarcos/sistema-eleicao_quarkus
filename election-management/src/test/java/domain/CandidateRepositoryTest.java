package domain;

import domain.candidate.Candidate;
import domain.candidate.CandidateQuery;
import domain.candidate.CandidateRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

public abstract class CandidateRepositoryTest {
    public abstract CandidateRepository repository();

    @Test
    void save(){
        Candidate candidate = Instancio.create(Candidate.class);
        repository().save(candidate);

        Optional<Candidate> result = repository().findById(candidate.id());
        assertTrue(result.isPresent());
        assertEquals(candidate, result.get());
    }

    @Test
    void findAll(){
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(5)
                .sorted(Comparator.comparing(Candidate::id))
                .collect(Collectors.toList());
        repository().save(candidates);

        List<Candidate> result = new ArrayList<>(repository().findAll());

        result.sort(Comparator.comparing(Candidate::id));
        assertEquals(result, candidates);
    }

    @Test
    void findByName(){
        Candidate candidate = Instancio.create(Candidate.class);
        Candidate candidate1 = Instancio.of(Candidate.class).set(field("familyName"), "Correa").create();

        CandidateQuery query = new CandidateQuery.Builder().name("COR").build();

        repository().save(List.of(candidate, candidate1));
        List<Candidate> result = repository().find(query);

        assertEquals(1, result.size());
        assertEquals(candidate1, result.get(0));
    }
}