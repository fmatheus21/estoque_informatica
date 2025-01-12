package br.com.fmatheus.app.controller.dto.response;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public record InvoiceResponse(
        UUID id,
        String number,
        String accessKey,
        String xmlFile,
        SupplierResponse supplier,
        Collection<InvoiceItemResponse> invoiceItems) {

    public record SupplierResponse(
            UUID id,
            LocalDateTime dateUpdated,
            UUID idUserUpdated,
            PersonResponse person) {

        public record PersonResponse(
                Long id,
                String name,
                String document,
                PersonTypeResponse personType) {

            public record PersonTypeResponse(
                    String name) {
            }
        }

    }

    public record InvoiceItemResponse(
            String serialNumber,
            String observation,
            UUID idUserCreated,
            LocalDateTime dateCreated) {
    }

}
