package br.com.fmatheus.app.controller.dto.filter;

public record InvoiceFilter(
        String supplierName,
        String supplierDocument,
        String invoiceNumber,
        String accessKey) {
}
