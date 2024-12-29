package br.com.fmatheus.app.model.repository.query;

import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.model.entity.NotaFiscal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotaFiscalRepositoryQuery {

    Page<NotaFiscal> findAllFilter(Pageable pageable, NotaFiscalFilter filter);

    Long total(NotaFiscalFilter filter);
}
