package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    private String name;

    @NotBlank
    @Size(min = 8, max = 14)
    private String ean;

    @Valid
    @NotNull
    private ManufacturerRequest manufacturer;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ManufacturerRequest {
        private @NotNull Long id;
        private @NotBlank String name;
    }

}
