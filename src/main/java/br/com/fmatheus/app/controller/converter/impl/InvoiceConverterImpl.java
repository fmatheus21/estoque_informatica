package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.controller.converter.InvoiceConverter;
import br.com.fmatheus.app.controller.dto.request.InvoiceRequest;
import br.com.fmatheus.app.controller.dto.response.InvoiceResponse;
import br.com.fmatheus.app.model.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InvoiceConverterImpl implements InvoiceConverter {

    private final ModelMapper mapper;

    @Override
    public Invoice converterToEntity(InvoiceRequest request) {
        return this.mapper.map(request, Invoice.class);
    }

    @Override
    public InvoiceResponse converterToResponse(Invoice invoice) {
        return this.mapper.map(invoice, InvoiceResponse.class);
    }

}
