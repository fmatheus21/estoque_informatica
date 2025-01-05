package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Fornecedor;

import java.util.Optional;
import java.util.UUID;

public interface FornecedorService extends GenericService<Fornecedor, UUID> {

    Optional<Fornecedor> findByDocument(String documento);
}
