package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank String name,
        @NotBlank String ean,
        @Valid @NotNull ManufacturerRequest manufacturer) {

    public record ManufacturerRequest(
            @NotNull Long id) {
    }
}
