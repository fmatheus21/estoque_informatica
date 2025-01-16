package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.filter.ProductFilter;
import br.com.fmatheus.app.controller.dto.request.ProductRequest;
import br.com.fmatheus.app.controller.dto.response.ProductResponse;
import br.com.fmatheus.app.controller.facade.ProductFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return this.facade.findById(id);
    }

    @Transactional(readOnly = true)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manufacturer/{id}")
    public ResponseEntity<?> findAllByManufacturer(@PathVariable Long id) {
        var query = this.facade.findAllByManufacturer(id);
        return !query.isEmpty() ? ResponseEntity.ok(query) : ResponseEntity.noContent().build();
    }


    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAllFilter(Pageable pageable, ProductFilter filter) {
        var result = this.facade.findAllFilter(pageable, filter);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }


    @GetMapping("/attributes")
    public Map<String, String> attributes() {
        Map<String, String> map = new HashMap<>();
        map.put("productName", "name");
        map.put("productEan", "ean");
        map.put("manufacturerName", "manufacturer.name");
        return map;
    }

}
