package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.NotaFiscalConverter;
import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.controller.dto.response.DanfeXmlResponse;
import br.com.fmatheus.app.controller.dto.response.NotaFiscalResponse;
import br.com.fmatheus.app.model.service.NotaFiscalService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class NotaFiscalFacade {

    private final NotaFiscalService notaFiscalService;

    private final NotaFiscalConverter notaFiscalConverter;


    public NotaFiscalResponse create(MultipartFile file) {

        try {
            XmlMapper xmlMapper = new XmlMapper();
            DanfeXmlResponse response = xmlMapper.readValue(file.getInputStream(), DanfeXmlResponse.class);
            System.out.println(response);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Page<NotaFiscalResponse> findAllFilter(Pageable pageable, NotaFiscalFilter filter) {
        var list = this.notaFiscalService.findAllFilter(pageable, filter);
        var listConverter = list.stream().map(this.notaFiscalConverter::converterToResponse).toList();
        return new PageImpl<>(listConverter, pageable, this.notaFiscalService.total(filter));
    }


}
