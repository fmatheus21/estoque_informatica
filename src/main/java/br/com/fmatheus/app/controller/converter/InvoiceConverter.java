package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.InvoiceRequest;
import br.com.fmatheus.app.controller.dto.response.InvoiceResponse;
import br.com.fmatheus.app.model.entity.Invoice;

public interface InvoiceConverter extends MapperConverter<Invoice, InvoiceRequest, InvoiceResponse> {
}
