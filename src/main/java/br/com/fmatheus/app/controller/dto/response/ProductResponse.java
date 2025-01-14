package br.com.fmatheus.app.controller.dto.response;

public record ProductResponse(
        String name,
        String ean,
        ManufacturerResponse manufacturer) {

    public record ManufacturerResponse(
            Long id,
            String name) {
    }
}
