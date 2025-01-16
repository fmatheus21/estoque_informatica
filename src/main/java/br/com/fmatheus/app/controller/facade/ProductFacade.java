package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.ProductConverter;
import br.com.fmatheus.app.controller.dto.filter.ProductFilter;
import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
public class ProductFacade {

    private final ProductConverter productConverter;
    private final ProductService productService;
    private final MessageFacade messageFacade;

    public ProductResponse create(ProductRequest request) {
        var query = this.productService.findByEan(request.ean());
        if (query.isPresent()) {
            throw this.messageFacade.errorEanAlready(request.ean());
        }

        var converter = this.productConverter.converterToEntity(request);
        var commit = this.productService.save(converter);

        return this.productConverter.converterToResponse(commit);
    }

    public ProductResponse findById(Long id) {
        var query = this.productService.findById(id).orElseThrow(this.messageFacade::errorNotFoundException);
        return this.productConverter.converterToResponse(query);
    }

    public Collection<ProductResponse> findAllByManufacturer(Long id) {
        var query = this.productService.findByIdManufacturer(id);
        return query.stream().map(this.productConverter::converterToResponse).toList();
    }

    public Page<ProductResponse> findAllFilter(Pageable pageable, ProductFilter filter) {
        var products = productService.findAllFilter(pageable, filter);
        var converted = products.stream().map(productConverter::converterToResponse).toList();
        return new PageImpl<>(converted, pageable, productService.total(filter));
    }

}
