package br.com.fmatheus.app.model.repository.impl;

import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.model.entity.NotaFiscal;
import br.com.fmatheus.app.model.repository.impl.restriction.NotaFiscalRestriction;
import br.com.fmatheus.app.model.repository.query.NotaFiscalRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class NotaFiscalRepositoryImpl extends NotaFiscalRestriction implements NotaFiscalRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NotaFiscal> findAllFilter(Pageable pageable, NotaFiscalFilter filter) {
        var builder = manager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> criteriaQuery = builder.createQuery(NotaFiscal.class);
        Root<NotaFiscal> root = criteriaQuery.from(NotaFiscal.class);
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery
                .where(predicates)
                .orderBy(builder.asc(root.get("id")));

        TypedQuery<NotaFiscal> typedQuery = manager.createQuery(criteriaQuery);

        addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    @Override
    public Long total(NotaFiscalFilter filter) {
        var builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<NotaFiscal> root = criteriaQuery.from(NotaFiscal.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
