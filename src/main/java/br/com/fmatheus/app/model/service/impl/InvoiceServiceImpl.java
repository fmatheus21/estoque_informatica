package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.converter.helper.InvoiceHelper;
import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.model.entity.Invoice;
import br.com.fmatheus.app.model.repository.InvoiceRepository;
import br.com.fmatheus.app.model.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.fmatheus.app.controller.util.CharacterUtil.removeAllSpaces;
import static br.com.fmatheus.app.controller.util.CharacterUtil.removeSpecialCharacters;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl extends InvoiceHelper implements InvoiceService {

    private final InvoiceRepository repository;

    @Override
    public List<Invoice> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Invoice> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        var input = input(invoice);
        return output(this.repository.save(input));
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Invoice> findByAccessKey(String accessKey) {
        return this.repository.findByAccessKey(removeAllSpaces(accessKey));
    }

    @Override
    public Collection<Invoice> findByNumber(String numero) {
        return this.repository.findByNumber(removeSpecialCharacters(numero));
    }

    @Override
    public Page<Invoice> findAllFilter(Pageable pageable, InvoiceFilter filter) {
        return this.repository.findAllFilter(pageable, filter);
    }

    @Override
    public Long total(InvoiceFilter filter) {
        return this.repository.total(filter);
    }

    @Override
    public Optional<Invoice> findBySerialNumber(String serialNumber) {
        return this.repository.findByInvoiceItems_SerialNumber(serialNumber);
    }

}
