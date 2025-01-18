package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    @Valid
    @NotNull
    private Collection<ProductRequest> products;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductRequest {

        @NotBlank
        private Long idProduct;

        @NotBlank
        private String ean;

        @Valid
        private Collection<InvoiceItemRequest> items;

        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class InvoiceItemRequest {
            @NotBlank
            private String serialNumber;
            private String observation;
        }

    }

}
