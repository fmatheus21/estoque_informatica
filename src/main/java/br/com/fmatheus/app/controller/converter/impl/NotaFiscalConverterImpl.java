package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.controller.converter.NotaFiscalConverter;
import br.com.fmatheus.app.controller.dto.request.NotaFiscalRequest;
import br.com.fmatheus.app.controller.dto.response.NotaFiscalResponse;
import br.com.fmatheus.app.model.entity.NotaFiscal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotaFiscalConverterImpl implements NotaFiscalConverter {

    private final ModelMapper mapper;

    @Override
    public NotaFiscal converterToEntity(NotaFiscalRequest request) {
        return this.mapper.map(request, NotaFiscal.class);
    }

    @Override
    public NotaFiscalResponse converterToResponse(NotaFiscal notaFiscal) {
        return this.mapper.map(notaFiscal, NotaFiscalResponse.class);
    }

}
