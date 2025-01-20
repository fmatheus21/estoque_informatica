package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.config.properties.CustomProperties;
import br.com.fmatheus.app.controller.converter.InvoiceConverter;
import br.com.fmatheus.app.controller.converter.SupplierConverter;
import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.controller.dto.request.InvoiceRequest;
import br.com.fmatheus.app.controller.dto.request.DanfeXmlRequest;
import br.com.fmatheus.app.controller.dto.response.DanfeReportResponse;
import br.com.fmatheus.app.controller.dto.response.InvoiceResponse;
import br.com.fmatheus.app.controller.exception.FileStorageException;
import br.com.fmatheus.app.controller.exception.JasperException;
import br.com.fmatheus.app.controller.report.service.DanfeReportService;
import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.controller.util.ConverterUtil;
import br.com.fmatheus.app.controller.util.DateFormatterUtil;
import br.com.fmatheus.app.controller.util.FileUtil;
import br.com.fmatheus.app.model.entity.*;
import br.com.fmatheus.app.model.service.InvoiceService;
import br.com.fmatheus.app.model.service.PersonService;
import br.com.fmatheus.app.model.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class InvoiceFacade {

    private static final String SEPARATOR = File.separator;
    private final ConverterUtil converterUtil;
    private final InvoiceService invoiceService;
    private final InvoiceConverter invoiceConverter;
    private final SupplierConverter supplierConverter;
    private final SupplierService supplierService;
    private final PersonService personService;
    private final DanfeReportService danfeReportService;
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

    public void viewInvoice(String accessKey, HttpServletResponse response) {

        var pathRoot = System.getProperty(this.properties.getUpload().getPathRoot());
        var pathFile = pathRoot.concat(SEPARATOR).concat(this.properties.getUpload().getFiles().getDanfe().getPath());
        var fileName = accessKey.concat(".xml");

        InputStream inputStream = null;
        try {
            inputStream = FileUtil.findFileAsInputStream(pathFile, fileName).orElseThrow(() -> new FileStorageException("Teste"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DanfeXmlRequest danfeXml = null;
        try {
            danfeXml = this.converterUtil.convertXmlToObject(inputStream, DanfeXmlRequest.class);
            System.out.println("danfeXml: " + danfeXml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var ide = danfeXml.getNFe().getInfNFe().getIde();
        var emitter = danfeXml.getNFe().getInfNFe().getEmit();
        var recipient = danfeXml.getNFe().getInfNFe().getDest();
        var protocol = danfeXml.getProtNFe();

        var danfe = DanfeReportResponse.builder()
                .invoiceNumber(ide.getCNF())
                .serie(ide.getSerie())
                .accessKey(CharacterUtil.formatMask(accessKey, "#### #### #### #### #### #### #### #### #### #### ####"))
                .natureOperation(ide.getNatOp())
                .emitterName(emitter.getXNome())
                .emitterDocument(CharacterUtil.formatCNPJ(emitter.getCnpj()))
                .emitterStateRegistration(emitter.getIe())
                .natureOperation(CharacterUtil.convertFirstUppercaseCharacter(ide.getNatOp()))
                .emitterAddress(
                        emitter.getEnderEmit().getXLgr()
                                .concat(",")
                                .concat(emitter.getEnderEmit().getNro())
                                .concat(emitter.getEnderEmit().getXCpl() != null ? " - " + emitter.getEnderEmit().getXCpl() : "")
                                .concat("\n")
                                .concat(emitter.getEnderEmit().getXBairro() + " - " + CharacterUtil.formatMask(emitter.getEnderEmit().getCep(), "####-###"))
                                .concat("\n")
                                .concat(emitter.getEnderEmit().getXMun() + " - " + emitter.getEnderEmit().getUf()))
                .recipientName(CharacterUtil.convertFirstUppercaseCharacter(recipient.getXNome()))
                .recipientDocument(CharacterUtil.formatCPF(recipient.getCpf()))
                .recipientAddress(
                        CharacterUtil.convertFirstUppercaseCharacter(recipient.getEnderDest().getXLgr()
                                .concat(", ")
                                .concat(recipient.getEnderDest().getNro())
                                .concat(recipient.getEnderDest().getXCpl() != null ? " - " + recipient.getEnderDest().getXCpl() : "")))
                .recipientDistrict(CharacterUtil.convertFirstUppercaseCharacter(recipient.getEnderDest().getXBairro()))
                .recipientZipCode(CharacterUtil.formatMask(recipient.getEnderDest().getCep(), "#####-###"))
                .recipientCity(CharacterUtil.convertFirstUppercaseCharacter(recipient.getEnderDest().getXMun()))
                .recipientFu(CharacterUtil.convertAllUppercaseCharacters(recipient.getEnderDest().getUf()))
                .authorizationProtocol(protocol.getInfProt().getNProt() + " - " + DateFormatterUtil.converterDate(protocol.getInfProt().getDhRecbto()))
                .build();

        try {
            this.danfeReportService.createPdf(Collections.singleton(danfe), response);
        } catch (JRException | IOException e) {
            throw new JasperException(e.getMessage());
        }
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
        var pathFile = pathRoot.concat(SEPARATOR).concat(this.properties.getUpload().getFiles().getDanfe().getPath());

        try {
            return FileUtil.saveFile(file, pathFile, response.getProtNFe().getInfProt().getChNFe());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw this.exceptionFacade.errorFileStorage();
        }
    }

}
