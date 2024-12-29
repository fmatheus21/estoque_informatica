package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NotaFiscalRequest(
        @NotBlank String numero,
        @NotBlank String chaveAcesso,
        @NotBlank String arquivoXml,
        @Valid @NotNull FornecedorRequest fornecedor) {

    public record FornecedorRequest(@NotNull UUID id) {
    }
}
