package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.model.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceService extends GenericService<Invoice, UUID> {

    Optional<Invoice> findByAccessKey(String accessKey);

    Collection<Invoice> findByNumber(String number);

    Page<Invoice> findAllFilter(Pageable pageable, InvoiceFilter filter);

    Long total(InvoiceFilter filter);

    Optional<Invoice> findBySerialNumber(String serialNumber);
}
