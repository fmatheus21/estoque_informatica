package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.NotaFiscalConverter;
import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.controller.dto.request.NotaFiscalRequest;
import br.com.fmatheus.app.controller.dto.response.DanfeXmlResponse;
import br.com.fmatheus.app.controller.dto.response.NotaFiscalResponse;
import br.com.fmatheus.app.controller.util.ConverterUtil;
import br.com.fmatheus.app.model.entity.*;
import br.com.fmatheus.app.model.service.FornecedorService;
import br.com.fmatheus.app.model.service.NotaFiscalService;
import br.com.fmatheus.app.model.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotaFiscalFacade {

    private final ConverterUtil converterUtil;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalConverter notaFiscalConverter;
    private final FornecedorService fornecedorService;
    private final PessoaService pessoaService;


    public NotaFiscalResponse create(MultipartFile file, String json) {
        NotaFiscalRequest request = null;
        try {
            request = this.converterUtil.convertJsonToObject(json, NotaFiscalRequest.class);
            log.info("request: {}", request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        DanfeXmlResponse response = null;

        try {
            response = this.converterUtil.convertXmlToObject(file.getInputStream(), DanfeXmlResponse.class);
            log.info("response: {}", response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            String xmlContent = this.converterUtil.convertXmlToString(file.getInputStream());
            log.info("xmlContent: {}", xmlContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.test(request, response);

        return null;

    }

    public Page<NotaFiscalResponse> findAllFilter(Pageable pageable, NotaFiscalFilter filter) {
        var list = this.notaFiscalService.findAllFilter(pageable, filter);
        var listConverter = list.stream().map(this.notaFiscalConverter::converterToResponse).toList();
        return new PageImpl<>(listConverter, pageable, this.notaFiscalService.total(filter));
    }

    private void test(NotaFiscalRequest request, DanfeXmlResponse response) {

        var cnpj = response.getNFe().getInfNFe().getEmit().getCnpj();
        var fornecedor = this.cadastrarFornecedor(response);

        var notaFiscal = NotaFiscal.builder()
                .fornecedor(fornecedor.getFornecedor())
                .numero(response.getNFe().getInfNFe().getIde().getCNF())
                .chaveAcesso(response.getProtNFe().getInfProt().getChNFe())
                .build();


    }

    private Pessoa cadastrarFornecedor(DanfeXmlResponse response) {

        var fornecedorConsulta = this.fornecedorService.findByDocument(response.getNFe().getInfNFe().getEmit().getCnpj());

        if (fornecedorConsulta.isPresent()) {
            log.info("O fornecedor {} ja existe e nao precisara ser cadastrado.", fornecedorConsulta.get().getPessoa().getNome());
            return fornecedorConsulta.get().getPessoa();
        }

        DanfeXmlResponse.NFe.InfNFe.Emit emit = response.getNFe().getInfNFe().getEmit();
        var cnpj = removeSpecialCharacters(emit.getCnpj());
        var razaoSocial = removeDuplicateSpace(convertAllUppercaseCharacters(emit.getXNome()));
        var telefone = emit.getEnderEmit().getFone();


        var pessoa = Pessoa.builder()
                .pessoaTipo(PessoaTipo.builder().id(2L).build())
                .nome(razaoSocial)
                .documento(cnpj)
                .dataCriacao(LocalDateTime.now())
                .idUsuarioCriacao(UUID.randomUUID())
                .build();

        var fornecedor = Fornecedor.builder()
                .pessoa(pessoa)
                .build();

        var contato = Contato.builder()
                .pessoa(pessoa)
                .telefone(removeSpecialCharacters(telefone))
                .build();

        pessoa.setFornecedor(fornecedor);
        pessoa.setContato(contato);

        return this.pessoaService.save(pessoa);
    }


}
