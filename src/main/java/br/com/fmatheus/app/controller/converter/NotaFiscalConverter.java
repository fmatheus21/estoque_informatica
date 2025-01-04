package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.NotaFiscalRequest;
import br.com.fmatheus.app.controller.dto.response.NotaFiscalResponse;
import br.com.fmatheus.app.model.entity.NotaFiscal;

public interface NotaFiscalConverter extends MapperConverter<NotaFiscal, NotaFiscalRequest, NotaFiscalResponse> {
}
