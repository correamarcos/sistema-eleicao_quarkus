package api;

import api.dto.in.Candidate;
import domain.candidate.CandidateService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CandidateApi {
    @Inject
    private CandidateService service;

    public CandidateApi(CandidateService service) {
        this.service = service;
    }

    public void create(Candidate request) {
        service.save(request.toDomain());
    }

    public api.dto.out.Candidate update(String id, Candidate dto) {
        service.save(dto.toDomain(id));
        return api.dto.out.Candidate.fromDomain(service.findById(id));
    }

    public List<api.dto.out.Candidate> list() {
        return service.findAll().stream().map(api.dto.out.Candidate::fromDomain).toList();
    }
}
