package api;

import api.dto.in.Candidate;
import domain.candidate.CandidateService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class CandidateApiTest {
    @Inject
    CandidateApi candidateApi;
    @InjectMock
    CandidateService candidateService;

    @Test
    void create(){
        Candidate dto = Instancio.create(Candidate.class);
        ArgumentCaptor<domain.candidate.Candidate> captor = ArgumentCaptor.forClass(domain.candidate.Candidate.class);
        candidateApi.create(dto);

        verify(candidateService).save(captor.capture());

        domain.candidate.Candidate candidate = captor.getValue();
        assertEquals(candidate.photo(), dto.photo());
        assertEquals(candidate.givenName(), dto.givenName());
        assertEquals(candidate.familyName(), dto.familyName());
        assertEquals(candidate.email(), dto.email());
        assertEquals(candidate.phone(), dto.phone());
        assertEquals(candidate.jobTitle(), dto.jobTitle());
    }

    @Test
    void update(){
        String id = UUID.randomUUID().toString();
        Candidate dto = Instancio.create(Candidate.class);
        domain.candidate.Candidate candidate = dto.toDomain(id);

        ArgumentCaptor<domain.candidate.Candidate> captor = ArgumentCaptor.forClass(domain.candidate.Candidate.class);
        when(candidateService.findById(id)).thenReturn(candidate);

        var response = candidateApi.update(id, dto);

        verify(candidateService).save(captor.capture());
        verify(candidateService).findById(id);

        assertEquals(api.dto.out.Candidate.fromDomain(candidate), response);
    }

    @Test
    void list(){
        List<domain.candidate.Candidate> candidates = Instancio.stream(domain.candidate.Candidate.class).limit(10).toList();

        when(candidateService.findAll()).thenReturn(candidates);

        List<api.dto.out.Candidate> response = candidateApi.list();

        verify(candidateService).findAll();

        assertEquals(candidates.stream().map(api.dto.out.Candidate::fromDomain).toList(), response);
    }
}