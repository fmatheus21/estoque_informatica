package br.com.fmatheus.app.controller.dto.filter;

public record NotaFiscalFilter(
        String nomeFornecedor,
        String documentoFornecedor,
        String numeroNotaFiscal,
        String chaveAcesso) {
}
