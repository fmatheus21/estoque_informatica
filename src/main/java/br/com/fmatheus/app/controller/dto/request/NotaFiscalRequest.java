package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record NotaFiscalRequest(@Valid @NotNull Collection<NotaFiscalItemRequest> itens) {

    public record NotaFiscalItemRequest(
            @NotNull Long idModelo,
            String observacao,
            @NotBlank String serialNumber) {
    }
}
