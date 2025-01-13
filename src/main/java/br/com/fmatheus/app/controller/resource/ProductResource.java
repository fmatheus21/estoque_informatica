package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.controller.facade.ProductFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        return this.facade.create(request);
    }

}
