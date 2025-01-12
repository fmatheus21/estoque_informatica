package br.com.fmatheus.app.model.repository.query;

import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.model.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceRepositoryQuery {

    Page<Invoice> findAllFilter(Pageable pageable, InvoiceFilter filter);

    Long total(InvoiceFilter filter);
}
