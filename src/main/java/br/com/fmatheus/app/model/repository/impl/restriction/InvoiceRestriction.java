package br.com.fmatheus.app.model.repository.impl.restriction;

import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.model.entity.Invoice;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.entity.Supplier;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.fmatheus.app.controller.util.CharacterUtil.removeDuplicateSpace;
import static br.com.fmatheus.app.controller.util.CharacterUtil.removeSpecialCharacters;

public abstract class InvoiceRestriction {

    private static final String PERCENT = "%";

    protected Predicate[] createRestrictions(InvoiceFilter filter, CriteriaBuilder builder, Root<Invoice> root) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Supplier, Invoice> joinSupplier = root.join("supplier");
        Join<Person, Supplier> joinPerson = joinSupplier.join("person");

        if (Objects.nonNull(filter.accessKey())) {
            predicates.add(builder.like(root.get("accessKey"), PERCENT + filter.accessKey() + PERCENT));
        }

        if (Objects.nonNull(filter.invoiceNumber())) {
            predicates.add(builder.like(root.get("number"), PERCENT + removeSpecialCharacters(filter.invoiceNumber()) + PERCENT));
        }

        if (Objects.nonNull(filter.supplierName())) {
            predicates.add(builder.like(builder.lower(joinPerson.get("name")), PERCENT + removeDuplicateSpace(filter.supplierName().toLowerCase()) + PERCENT));
        }

        if (Objects.nonNull(filter.supplierDocument())) {
            predicates.add(builder.like(builder.lower(joinPerson.get("document")), PERCENT + removeSpecialCharacters(filter.supplierDocument()) + PERCENT));
        }

        if (Objects.nonNull(filter.searchXml())) {
            predicates.add(builder.like(root.get("xmlFile"), PERCENT + removeDuplicateSpace(filter.searchXml()) + PERCENT));
        }

        return predicates.toArray(new Predicate[0]);

    }


    /**
     * Metodo responsavel por criar paginacao.
     *
     * @param typedQuery - TypedQuery
     * @param pageable   - Pageable
     * @author Fernando Matheus
     */
    protected void addPageRestrictions(TypedQuery<Invoice> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;
        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

}
