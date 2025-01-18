package br.com.fmatheus.app.controller.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String ean;
    private ManufacturerResponse manufacturer;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ManufacturerResponse {
        private Long id;
        private String name;
    }
}
