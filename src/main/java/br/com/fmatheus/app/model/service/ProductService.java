package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Product;

import java.util.Optional;

public interface ProductService extends GenericService<Product, Long> {

    Optional<Product> findByEan(String ean);
}
