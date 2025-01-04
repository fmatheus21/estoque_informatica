package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.controller.dto.request.NotaFiscalRequest;
import br.com.fmatheus.app.controller.dto.response.NotaFiscalResponse;
import br.com.fmatheus.app.controller.facade.NotaFiscalFacade;
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
@RequestMapping("/notas-fiscais")
public class NotaFiscalResource {

    private final NotaFiscalFacade facade;


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscalResponse create( @RequestParam(name = "file") MultipartFile file) {
        return this.facade.create(file);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<Page<NotaFiscalResponse>> findAllFilter(Pageable pageable, NotaFiscalFilter filter) {
        var result = this.facade.findAllFilter(pageable, filter);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

}
