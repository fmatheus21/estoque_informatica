package br.com.fmatheus.app.model.repository.impl;

import br.com.fmatheus.app.controller.dto.filter.ProductFilter;
import br.com.fmatheus.app.model.entity.Product;
import br.com.fmatheus.app.model.repository.impl.restriction.ProductRestriction;
import br.com.fmatheus.app.model.repository.query.ProductRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ProductRepositoryImpl extends ProductRestriction implements ProductRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Product> findAllFilter(Pageable pageable, ProductFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Product.class);
        var root = criteriaQuery.from(Product.class);
        var predicates = createRestrictions(filter, builder, root);

        criteriaQuery.where(predicates);

        this.applySorting(pageable, builder, criteriaQuery, root);

        var typedQuery = manager.createQuery(criteriaQuery);

        addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    /**
     * Aplica a ordenacaoo com base no Pageable fornecido.
     *
     * @param pageable      Pageable contendo informacoes de ordenacao.
     * @param builder       CriteriaBuilder para construir a ordenacao.
     * @param criteriaQuery Consulta CriteriaQuery que sera modificada.
     * @param root          Raiz da entidade Product.
     */
    private void applySorting(Pageable pageable, CriteriaBuilder builder, CriteriaQuery<Product> criteriaQuery, Root<Product> root) {

        if (pageable.getSort().isUnsorted()) {
            return;
        }

        var orders = pageable.getSort().stream()
                .map(order -> {
                    var property = order.getProperty();

                    if (property.contains(".")) {
                        var parts = property.split("\\.");
                        if (parts.length == 2 && parts[0].equals("manufacturer")) {
                            var manufacturerJoin = root.join("manufacturer");
                            return order.isAscending() ? builder.asc(manufacturerJoin.get(parts[1])) : builder.desc(manufacturerJoin.get(parts[1]));
                        }
                    }
                    return order.isAscending() ? builder.asc(root.get(property)) : builder.desc(root.get(property));
                }).toList();

        criteriaQuery.orderBy(orders);
    }

    @Override
    public Long total(ProductFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Long.class);
        var root = criteriaQuery.from(Product.class);

        var predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
