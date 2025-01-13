package br.com.fmatheus.app.controller.dto.response;

public record ProductResponse(
        String name,
        String ean,
        ManufacturerRequest idManufacturer) {

    public record ManufacturerRequest(
            Long id,
            String name) {
    }
}
