package br.com.fmatheus.app.controller.dto.response;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public record NotaFiscalResponse(
        UUID id,
        String numero,
        String chaveAcesso,
        String arquivoXml,
        FornecedorResponse fornecedor,
        Collection<NotaFiscalItemResponse> notaFiscalItems) {

    public record FornecedorResponse(
            UUID id,
            LocalDateTime dataAlteracao,
            UUID idUsuarioAlteracao,
            PessoaResponse pessoa) {

        public record PessoaResponse(
                Long id,
                String nome,
                String documento,
                PessoaTipoResponse pessoaTipo) {

            public record PessoaTipoResponse(
                    String nome) {
            }
        }

    }

    public record NotaFiscalItemResponse(
            String serialNumber,
            String observacao,
            UUID idUsuarioCriacao,
            LocalDateTime dataCriacao,
            ModeloResponse modelo) {

        public record ModeloResponse(
                String partnumber,
                String nome,
                FabricanteResponse fabricante,
                ProdutoResponse produto) {

            public record FabricanteResponse(
                    String nome) {
            }

            public record ProdutoResponse(
                    String nome) {
            }

        }

    }

}
