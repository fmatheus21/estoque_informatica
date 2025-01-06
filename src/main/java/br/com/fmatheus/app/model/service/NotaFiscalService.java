package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.model.entity.NotaFiscal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface NotaFiscalService extends GenericService<NotaFiscal, UUID> {

    Optional<NotaFiscal> findByChaveAcesso(String chaveAcesso);

    Collection<NotaFiscal> findByNumero(String numero);

    Page<NotaFiscal> findAllFilter(Pageable pageable, NotaFiscalFilter filter);

    Long total(NotaFiscalFilter filter);

    Optional<NotaFiscal> findBySerialNumber(String serialNumber);
}
