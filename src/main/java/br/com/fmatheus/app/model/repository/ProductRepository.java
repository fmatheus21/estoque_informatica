package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Product;
import br.com.fmatheus.app.model.repository.query.ProductRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

    Optional<Product> findByEan(String ean);

    Collection<Product> findByManufacturer_Id(Long id);

}
