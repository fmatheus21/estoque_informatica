package br.com.fmatheus.app.model.repository.impl.restriction;

import br.com.fmatheus.app.controller.dto.filter.ProductFilter;
import br.com.fmatheus.app.model.entity.Manufacturer;
import br.com.fmatheus.app.model.entity.Product;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

public abstract class ProductRestriction {

    private static final String PERCENT = "%";

    protected Predicate[] createRestrictions(ProductFilter filter, CriteriaBuilder builder, Root<Product> root) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Manufacturer, Product> joinManufacturer = root.join("manufacturer");


        if (Objects.nonNull(filter.productEan())) {
            predicates.add(builder.like(root.get("ean"), PERCENT + removeAllSpaces(filter.productEan()) + PERCENT));
        }

        if (Objects.nonNull(filter.productName())) {
            predicates.add(builder.like(root.get("name"), PERCENT + removeDuplicateSpace(filter.productName()) + PERCENT));
        }

        if (Objects.nonNull(filter.manufacturerName())) {
            predicates.add(builder.like(builder.lower(joinManufacturer.get("name")), PERCENT + removeDuplicateSpace(filter.manufacturerName().toUpperCase()) + PERCENT));
        }

        return predicates.toArray(new Predicate[0]);

    }


    /**
     * Metodo responsavel por criar paginacao.
     *
     * @param typedQuery - TypedQuery
     * @param pageable   - Pageable
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    protected void addPageRestrictions(TypedQuery<Product> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;
        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

}
