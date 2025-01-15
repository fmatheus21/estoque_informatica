package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.controller.dto.response.InvoiceResponse;
import br.com.fmatheus.app.controller.facade.InvoiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceResource {

    private final InvoiceFacade facade;


    @Transactional
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse create(@RequestPart(name = "file") MultipartFile file, @Valid @RequestPart("json") String json) {
        return this.facade.create(file, json);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<Page<InvoiceResponse>> findAllFilter(Pageable pageable, InvoiceFilter filter) {
        var query = this.facade.findAllFilter(pageable, filter);
        return !query.isEmpty() ? ResponseEntity.ok(query) : ResponseEntity.noContent().build();
    }

}
