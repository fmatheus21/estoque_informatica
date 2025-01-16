package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.controller.converter.ProductConverter;
import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.model.entity.Manufacturer;
import br.com.fmatheus.app.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductConverterImpl implements ProductConverter {

    @Override
    public Product converterToEntity(ProductRequest request) {
        var product = new Product();
        BeanUtils.copyProperties(request, product);

        var manufacturer = new Manufacturer();
        BeanUtils.copyProperties(request.manufacturer(), manufacturer);

        product.setManufacturer(manufacturer);

        return product;
    }

    @Override
    public ProductResponse converterToResponse(Product product) {
        var manufacturer = new ProductResponse.ManufacturerResponse(product.getManufacturer().getId(), product.getManufacturer().getName());
        return new ProductResponse(product.getId(), product.getName(), product.getEan(), manufacturer);
    }
}
