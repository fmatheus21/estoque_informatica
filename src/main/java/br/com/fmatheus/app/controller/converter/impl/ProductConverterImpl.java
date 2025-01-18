package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.controller.converter.ProductConverter;
import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductConverterImpl implements ProductConverter {

    private final ModelMapper mapper;

    @Override
    public Product converterToEntity(ProductRequest request) {
        return this.mapper.map(request, Product.class);
    }

    @Override
    public ProductResponse converterToResponse(Product product) {
        return this.mapper.map(product, ProductResponse.class);
    }

}
