package br.com.fmatheus.app.model.repository.impl;

import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.model.entity.Invoice;
import br.com.fmatheus.app.model.repository.impl.restriction.InvoiceRestriction;
import br.com.fmatheus.app.model.repository.query.InvoiceRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class InvoiceRepositoryImpl extends InvoiceRestriction implements InvoiceRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Invoice> findAllFilter(Pageable pageable, InvoiceFilter filter) {
        var builder = manager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = builder.createQuery(Invoice.class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery
                .where(predicates)
                .orderBy(builder.asc(root.get("id")));

        TypedQuery<Invoice> typedQuery = manager.createQuery(criteriaQuery);

        addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    @Override
    public Long total(InvoiceFilter filter) {
        var builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
