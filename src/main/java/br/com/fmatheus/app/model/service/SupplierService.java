package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Supplier;

import java.util.Optional;
import java.util.UUID;

public interface SupplierService extends GenericService<Supplier, UUID> {

    Optional<Supplier> findByDocument(String document);
}
