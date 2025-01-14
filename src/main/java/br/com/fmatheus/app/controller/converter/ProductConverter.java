package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.model.entity.Product;

public interface ProductConverter extends MapperConverter<Product, ProductRequest, ProductResponse> {
}
