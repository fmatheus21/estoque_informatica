package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.ProductConverter;
import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

}
