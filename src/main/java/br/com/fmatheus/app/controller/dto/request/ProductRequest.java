package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotBlank @Size(min = 1, max = 200) String name,
        @NotBlank @Size(min = 8, max = 14) String ean,
        @Valid @NotNull ManufacturerRequest manufacturer) {

    public record ManufacturerRequest(
            @NotNull Long id,
            @NotBlank String name) {
    }
}
