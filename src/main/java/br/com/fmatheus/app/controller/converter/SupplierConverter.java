package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.DanfeXmlRequest;
import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.entity.Product;

public interface SupplierConverter extends MapperConverter<Person, DanfeXmlRequest, Object> {
}
