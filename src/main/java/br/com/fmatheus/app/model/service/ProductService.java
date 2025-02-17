package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.controller.dto.filter.ProductFilter;
import br.com.fmatheus.app.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface ProductService extends GenericService<Product, Long> {

    Optional<Product> findByEan(String ean);

    Page<Product> findAllFilter(Pageable pageable, ProductFilter filter);

    Long total(ProductFilter filter);

    Collection<Product> findByIdManufacturer(Long idManufacturer);
}
