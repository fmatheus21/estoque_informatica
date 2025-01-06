package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

import br.com.fmatheus.app.model.entity.NotaFiscal;
import br.com.fmatheus.app.model.repository.NotaFiscalRepository;
import br.com.fmatheus.app.model.service.NotaFiscalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotaFiscalRepository repository;

    @Override
    public List<NotaFiscal> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<NotaFiscal> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public NotaFiscal save(NotaFiscal notaFiscal) {
        return this.repository.save(notaFiscal);
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<NotaFiscal> findByChaveAcesso(String chaveAcesso) {
        return this.repository.findByChaveAcesso(removeAllSpaces(chaveAcesso));
    }

    @Override
    public Collection<NotaFiscal> findByNumero(String numero) {
        return this.repository.findByNumero(removeSpecialCharacters(numero));
    }

    @Override
    public Page<NotaFiscal> findAllFilter(Pageable pageable, NotaFiscalFilter filter) {
        return this.repository.findAllFilter(pageable, filter);
    }

    @Override
    public Long total(NotaFiscalFilter filter) {
        return this.repository.total(filter);
    }

    @Override
    public Optional<NotaFiscal> findBySerialNumber(String serialNumber) {
        return this.repository.findByNotaFiscalItems_SerialNumber(serialNumber);
    }

}
