package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.InvoiceConverter;
import br.com.fmatheus.app.controller.dto.filter.InvoiceFilter;
import br.com.fmatheus.app.controller.dto.request.InvoiceRequest;
import br.com.fmatheus.app.controller.dto.response.DanfeXmlResponse;
import br.com.fmatheus.app.controller.dto.response.InvoiceResponse;
import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.controller.util.ConverterUtil;
import br.com.fmatheus.app.model.entity.*;
import br.com.fmatheus.app.model.service.SupplierService;
import br.com.fmatheus.app.model.service.InvoiceService;
import br.com.fmatheus.app.model.service.PersonService;
import br.com.fmatheus.app.model.service.ProductService;
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
public class InvoiceFacade {

    private final ConverterUtil converterUtil;
    private final InvoiceService invoiceService;
    private final InvoiceConverter invoiceConverter;
    private final SupplierService supplierService;
    private final PersonService personService;
    private final ProductService productService;


    public InvoiceResponse create(MultipartFile file, String json) {
        InvoiceRequest request = this.convertJsonToObject(json);
        DanfeXmlResponse response = this.convertXmlToObject(file);

        this.validatesInvoiceData(request, response);

        String xml = this.convertXmlToString(file);

        var supplier = this.registerSupplier(response);
        this.registerProducts(response);

        var invoice = Invoice.builder()
                .supplier(supplier.getSupplier())
                .number(response.getNFe().getInfNFe().getIde().getCNF())
                .accessKey(response.getProtNFe().getInfProt().getChNFe())
                .xmlFile(xml)
                .build();

        Collection<String> listEan = new ArrayList<>();
        response.getNFe().getInfNFe().getDet().forEach(prod -> listEan.add(prod.getProd().getCEan()));

        Collection<InvoiceItem> items = new ArrayList<>();
        request.items().forEach(item -> {

            var ean = listEan.stream().filter(f -> f.equalsIgnoreCase(item.ean())).findFirst().orElseThrow(RuntimeException::new);
            var produto = this.productService.findByEan(ean).orElseThrow(RuntimeException::new);

            items.add(InvoiceItem.builder()
                    .invoice(invoice)
                    .product(produto)
                    .serialNumber(item.serialNumber())
                    .observation(item.observation())
                    .idUserCreated(UUID.randomUUID())
                    .dateCreated(LocalDateTime.now())
                    .build());
        });

        invoice.setInvoiceItems(items);

        this.invoiceService.save(invoice);

        return null;

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


    private Person registerSupplier(DanfeXmlResponse response) {

        DanfeXmlResponse.NFe.InfNFe.Emit emit = response.getNFe().getInfNFe().getEmit();
        var document = removeSpecialCharacters(emit.getCnpj());
        log.info("Consultando o supplier com o CNPJ {}.", document);

        var supplierQuery = this.supplierService.findByDocument(document);

        if (supplierQuery.isPresent()) {
            return supplierQuery.get().getPerson();
        }

        var name = removeDuplicateSpace(convertAllUppercaseCharacters(emit.getXNome()));
        var telefone = emit.getEnderEmit().getFone();
        log.info("O Fornecedor {} sera cadastrado.", name);

        var person = Person.builder()
                .personType(PersonType.builder().id(2L).build())
                .name(name)
                .document(document)
                .dateCreated(LocalDateTime.now())
                .idUserCreated(UUID.randomUUID())
                .build();

        var supplier = Supplier.builder()
                .person(person)
                .build();

        var contact = Contact.builder()
                .person(person)
                .phone(removeSpecialCharacters(telefone))
                .build();

        person.setSupplier(supplier);
        person.setContact(contact);

        return this.personService.save(person);
    }


    private void registerProducts(DanfeXmlResponse response) {
        response.getNFe().getInfNFe().getDet().forEach(prod -> {
            DanfeXmlResponse.NFe.InfNFe.Det.Prod prodXml = prod.getProd();
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
    }

    private void validatesInvoiceData(InvoiceRequest request, DanfeXmlResponse response) {
        request.items().forEach(item -> {
            var serialNumberQuery = this.invoiceService.findBySerialNumber(item.serialNumber());
            if (serialNumberQuery.isPresent()) {
                throw new RuntimeException(String.format("O SerialNumber %s já está cadastrado.", item.serialNumber()));
            }
        });

        var accessKeyQuery = this.invoiceService.findByAccessKey(response.getProtNFe().getInfProt().getChNFe());
        if (accessKeyQuery.isPresent()) {
            throw new RuntimeException(String.format("A Chave de Acesso %s já está cadastrada.", accessKeyQuery.get().getAccessKey()));
        }

    }


}
