package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.NotaFiscalConverter;
import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.controller.dto.request.NotaFiscalRequest;
import br.com.fmatheus.app.controller.dto.response.DanfeXmlResponse;
import br.com.fmatheus.app.controller.dto.response.NotaFiscalResponse;
import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.controller.util.ConverterUtil;
import br.com.fmatheus.app.model.entity.*;
import br.com.fmatheus.app.model.service.FornecedorService;
import br.com.fmatheus.app.model.service.NotaFiscalService;
import br.com.fmatheus.app.model.service.PessoaService;
import br.com.fmatheus.app.model.service.ProdutoService;
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
import java.util.ArrayList;
import java.util.Collection;
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
    private final ProdutoService produtoService;


    public NotaFiscalResponse create(MultipartFile file, String json) {
        NotaFiscalRequest request = this.convertJsonToObject(json);
        DanfeXmlResponse response = this.convertXmlToObject(file);

        this.validaDadosNotaFiscal(request, response);

        String xml = this.convertXmlToString(file);

        var fornecedor = this.cadastrarFornecedor(response);
        this.cadastrarProdutos(response);

        var notaFiscal = NotaFiscal.builder()
                .fornecedor(fornecedor.getFornecedor())
                .numero(response.getNFe().getInfNFe().getIde().getCNF())
                .chaveAcesso(response.getProtNFe().getInfProt().getChNFe())
                .arquivoXml(xml)
                .build();

        Collection<String> lisEan = new ArrayList<>();
        response.getNFe().getInfNFe().getDet().forEach(prod -> lisEan.add(prod.getProd().getCEan()));

        Collection<NotaFiscalItem> itens = new ArrayList<>();
        request.itens().forEach(item -> {

            var ean = lisEan.stream().filter(f -> f.equalsIgnoreCase(item.ean())).findFirst().orElseThrow(RuntimeException::new);
            var produto = this.produtoService.findByEan(ean).orElseThrow(RuntimeException::new);

            itens.add(NotaFiscalItem.builder()
                    .notaFiscal(notaFiscal)
                    .produto(produto)
                    .serialNumber(item.serialNumber())
                    .observacao(item.observacao())
                    .idUsuarioCriacao(UUID.randomUUID())
                    .dataCriacao(LocalDateTime.now())
                    .build());
        });

        notaFiscal.setNotaFiscalItems(itens);

        this.notaFiscalService.save(notaFiscal);

        return null;

    }


    public Page<NotaFiscalResponse> findAllFilter(Pageable pageable, NotaFiscalFilter filter) {
        var list = this.notaFiscalService.findAllFilter(pageable, filter);
        var listConverter = list.stream().map(this.notaFiscalConverter::converterToResponse).toList();
        return new PageImpl<>(listConverter, pageable, this.notaFiscalService.total(filter));
    }

    private NotaFiscalRequest convertJsonToObject(String json) {
        try {
            log.info("Convertendo o json recebido na requisicao");
            return this.converterUtil.convertJsonToObject(json, NotaFiscalRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private DanfeXmlResponse convertXmlToObject(MultipartFile file) {
        try {
            log.info("Convertendo o arquivo XML para objeto");
            return this.converterUtil.convertXmlToObject(file.getInputStream(), DanfeXmlResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertXmlToString(MultipartFile file) {
        try {
            log.info("Convertendo o arquivo XML para String");
            return this.converterUtil.convertXmlToString(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Pessoa cadastrarFornecedor(DanfeXmlResponse response) {

        DanfeXmlResponse.NFe.InfNFe.Emit emit = response.getNFe().getInfNFe().getEmit();
        var cnpj = removeSpecialCharacters(emit.getCnpj());
        log.info("Consultando o fornecedor com o CNPJ {}.", cnpj);

        var fornecedorConsulta = this.fornecedorService.findByDocument(cnpj);

        if (fornecedorConsulta.isPresent()) {
            return fornecedorConsulta.get().getPessoa();
        }

        var razaoSocial = removeDuplicateSpace(convertAllUppercaseCharacters(emit.getXNome()));
        var telefone = emit.getEnderEmit().getFone();
        log.info("O fornecedor {} sera cadastrado.", razaoSocial);

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


    private void cadastrarProdutos(DanfeXmlResponse response) {

        response.getNFe().getInfNFe().getDet().forEach(prod -> {
            DanfeXmlResponse.NFe.InfNFe.Det.Prod prodXml = prod.getProd();
            String ean = prodXml.getCEan();

            var consulta = this.produtoService.findByEan(ean);
            if (consulta.isEmpty()) {
                log.info("Salvando o produto [{}]", prodXml.getXProd());
                var produto = Produto.builder()
                        .nome(CharacterUtil.removeDuplicateSpace(prodXml.getXProd()))
                        .ean(ean)
                        .build();
                this.produtoService.save(produto);
            }
        });


    }

    private void validaDadosNotaFiscal(NotaFiscalRequest request, DanfeXmlResponse response) {
        request.itens().forEach(item -> {
            var consultaSerialNumber = this.notaFiscalService.findBySerialNumber(item.serialNumber());
            if (consultaSerialNumber.isPresent()) {
                throw new RuntimeException(String.format("O SerialNumber %s já está cadastrado.", item.serialNumber()));
            }
        });

        var consultaChaveAcesso = this.notaFiscalService.findByChaveAcesso(response.getProtNFe().getInfProt().getChNFe());
        if (consultaChaveAcesso.isPresent()) {
            throw new RuntimeException(String.format("A Chave de Acesso %s já está cadastrada.", consultaChaveAcesso.get().getChaveAcesso()));
        }

    }


}
