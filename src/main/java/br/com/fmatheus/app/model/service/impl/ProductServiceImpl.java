package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.converter.helper.ProductHelper;
import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.model.entity.Product;
import br.com.fmatheus.app.model.repository.ProductRepository;
import br.com.fmatheus.app.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl extends ProductHelper implements ProductService {

    private final ProductRepository repository;

    @Override
    public Optional<Product> findByEan(String ean) {
        var query = this.repository.findByEan(CharacterUtil.removeAllSpaces(ean));
        return query.map(this::output);
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Product> findById(Long id) {
        var query = this.repository.findById(id);
        return query.map(this::output);
    }

    @Override
    public Product save(Product product) {
        var input = input(product);
        return output(this.repository.save(input));
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }


}
