package infrastructure.repositories;

import domain.candidate.Candidate;
import domain.candidate.CandidateQuery;
import domain.candidate.CandidateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class SQLCandidateRepository implements CandidateRepository {
    private EntityManager entityManager;

    public SQLCandidateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(List<Candidate> candidates) {
        candidates.stream()
                .map(infrastructure.repositories.entities.Candidate::fromDomain)
                .forEach(entityManager::merge);
    }

    @Override
    public List<Candidate> find(CandidateQuery query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<infrastructure.repositories.entities.Candidate> cq =
                cb.createQuery(infrastructure.repositories.entities.Candidate.class);
        Root<infrastructure.repositories.entities.Candidate> root =
                cq.from(infrastructure.repositories.entities.Candidate.class);

//        var where = query.ids().map(id -> cb.in(root.get("id")).value(id)).get();
        cq.select(root).where(conditions(cb, query, root));

        return entityManager.createQuery(cq)
                .getResultStream()
                .map(infrastructure.repositories.entities.Candidate::toDomain)
                .toList();
    }

    private Predicate[] conditions(CriteriaBuilder cb,
                                   CandidateQuery query,
                                   Root<infrastructure.repositories.entities.Candidate> root){
        return Stream.of(query.ids().map(id -> cb.in(root.get("id")).value(id)),
                query.name().map(name -> cb.or(cb.like(cb.lower(root.get("family_name")), name.toLowerCase() + "%"),
                                                cb.like(cb.lower(root.get("given_name")), name.toLowerCase() + "%"))))
                        .flatMap(Optional::stream)
                .toArray(Predicate[]::new);
    }
}
