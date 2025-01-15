package br.com.fmatheus.app.model.repository.query;

import br.com.fmatheus.app.controller.dto.filter.ProductFilter;
import br.com.fmatheus.app.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQuery {

    Page<Product> findAllFilter(Pageable pageable, ProductFilter filter);

    Long total(ProductFilter filter);
}
