package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private UUID id;
    private String number;
    private String accessKey;
    private SupplierResponse supplier;
    private Collection<InvoiceItemResponse> invoiceItems;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SupplierResponse {
        private UUID id;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        private LocalDateTime dateUpdated;
        private UUID idUserUpdated;
        private PersonResponse person;

        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PersonResponse {
            private String name;
            private String document;
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceItemResponse {
        private String serialNumber;
        private String asset;
        private String observation;
        private UUID idUserCreated;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        private LocalDateTime dateCreated;
    }

}
