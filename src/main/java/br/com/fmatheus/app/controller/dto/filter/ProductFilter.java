package br.com.fmatheus.app.controller.dto.filter;

public record ProductFilter(
        String productName,
        String productEan,
        String manufacturerName) {
}
