package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.config.properties.CustomProperties;
import br.com.fmatheus.app.controller.converter.InvoiceConverter;
import br.com.fmatheus.app.controller.converter.SupplierConverter;
import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.controller.dto.request.InvoiceRequest;
import br.com.fmatheus.app.controller.dto.request.DanfeXmlRequest;
import br.com.fmatheus.app.controller.dto.response.InvoiceResponse;
import br.com.fmatheus.app.controller.util.ConverterUtil;
import br.com.fmatheus.app.controller.util.FileUtil;
import br.com.fmatheus.app.model.entity.*;
import br.com.fmatheus.app.model.service.InvoiceService;
import br.com.fmatheus.app.model.service.PersonService;
import br.com.fmatheus.app.model.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class InvoiceFacade {


    private final ConverterUtil converterUtil;
    private final InvoiceService invoiceService;
    private final InvoiceConverter invoiceConverter;
    private final SupplierConverter supplierConverter;
    private final SupplierService supplierService;
    private final PersonService personService;
    private final CustomProperties properties;
    private final ExceptionFacade exceptionFacade;


    public InvoiceResponse create(MultipartFile multipartFile, String json) {

        var danfeXml = this.convertXmlToObject(multipartFile);
        var file = this.saveFile(multipartFile, danfeXml);

        var request = this.convertJsonToObject(json);
        this.validatesInvoiceData(request, danfeXml);

        var xml = this.convertXmlToString(file);
        var supplier = this.registerSupplier(danfeXml);

        var invoice = Invoice.builder()
                .supplier(supplier.getSupplier())
                .number(danfeXml.getNFe().getInfNFe().getIde().getCNF())
                .accessKey(danfeXml.getProtNFe().getInfProt().getChNFe())
                .xmlFile(xml)
                .build();

        Collection<InvoiceItem> items = new ArrayList<>();

        request.getProducts().forEach(prod -> prod.getItems().forEach(it -> items.add(InvoiceItem.builder()
                .invoice(invoice)
                .serialNumber(it.getSerialNumber())
                .observation(it.getObservation())
                .idUserCreated(UUID.randomUUID())
                .dateCreated(LocalDateTime.now())
                .product(Product.builder()
                        .id(prod.getIdProduct())
                        .build())
                .build())));

        invoice.setInvoiceItems(items);

        var commit = this.invoiceService.save(invoice);

        return this.invoiceConverter.converterToResponse(commit);

    }


    public Page<InvoiceResponse> findAllFilter(Pageable pageable, InvoiceFilter filter) {
        var list = this.invoiceService.findAllFilter(pageable, filter);
        var listConverter = list.stream().map(this.invoiceConverter::converterToResponse).toList();
        return new PageImpl<>(listConverter, pageable, this.invoiceService.total(filter));
    }

    private InvoiceRequest convertJsonToObject(String json) {
        try {
            log.info("Convertendo o json recebido na requisicao");
            return this.converterUtil.convertJsonToObject(json, InvoiceRequest.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw this.exceptionFacade.errorJsonConverter();
        }
    }

    private DanfeXmlRequest convertXmlToObject(MultipartFile file) {
        try {
            log.info("Convertendo o arquivo XML para objeto");
            return this.converterUtil.convertXmlToObject(file.getInputStream(), DanfeXmlRequest.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw this.exceptionFacade.errorXmlConverter();
        }
    }

    private String convertXmlToString(File file) {
        try {
            log.info("Convertendo o arquivo XML para String");
            return this.converterUtil.convertXmlToString(file);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw this.exceptionFacade.errorXmlConverter();
        }
    }


    private Person registerSupplier(DanfeXmlRequest danfeXml) {

        DanfeXmlRequest.NFe.InfNFe.Emit emit = danfeXml.getNFe().getInfNFe().getEmit();
        var document = removeSpecialCharacters(emit.getCnpj());
        log.info("Consultando o supplier com o CNPJ {}.", document);

        var supplierQuery = this.supplierService.findByDocument(document);

        if (supplierQuery.isPresent()) {
            return supplierQuery.get().getPerson();
        }

        var converter = this.supplierConverter.converterToEntity(danfeXml);

        return this.personService.save(converter);

    }


    /*private void registerProducts(DanfeXmlRequest response) {
        response.getNFe().getInfNFe().getDet().forEach(prod -> {
            DanfeXmlRequest.NFe.InfNFe.Det.Prod prodXml = prod.getProd();
            String ean = prodXml.getCEan();

            var query = this.productService.findByEan(ean);
            if (query.isEmpty()) {
                log.info("Salvando o produto [{}]", prodXml.getXProd());
                var product = Product.builder()
                        .name(CharacterUtil.removeDuplicateSpace(prodXml.getXProd()))
                        .ean(ean)
                        .build();
                this.productService.save(product);
            }
        });
    }*/

    private void validatesInvoiceData(InvoiceRequest request, DanfeXmlRequest response) {

        var accessKeyQuery = this.invoiceService.findByAccessKey(response.getProtNFe().getInfProt().getChNFe());
        if (accessKeyQuery.isPresent()) {
            throw this.exceptionFacade.errorAccessKeyAlready();
        }

        request.getProducts().forEach(prod -> prod.getItems().forEach(it -> {
            var serialNumberQuery = this.invoiceService.findBySerialNumber(it.getSerialNumber());
            if (serialNumberQuery.isPresent()) {
                throw this.exceptionFacade.errorSerialNumberAlready(it.getSerialNumber());
            }
        }));
    }

    /**
     * Salva o arquivo recebido na requisicao.
     *
     * @param file     Arquivo a ser salvo.
     * @param response Arquivo XML convertido em objeto.
     */
    private File saveFile(MultipartFile file, DanfeXmlRequest response) {
        var pathRoot = System.getProperty(this.properties.getUpload().getPathRoot());
        var pathFile = pathRoot.concat(File.separator).concat(this.properties.getUpload().getFiles().getDanfe().getPath());

        try {
            return FileUtil.saveFile(file, pathFile, response.getProtNFe().getInfProt().getChNFe());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw this.exceptionFacade.errorFileStorage();
        }
    }

}
