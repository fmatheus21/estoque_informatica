package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record InvoiceRequest(
        @Valid @NotNull Collection<ProductRequest> products) {

    public record ProductRequest(
            @NotBlank Long idProduct,
            @NotBlank String ean,
            @Valid Collection<InvoiceItemRequest> items) {

        public record InvoiceItemRequest(
                @NotBlank String serialNumber,
                String observation) {
        }

    }

}
