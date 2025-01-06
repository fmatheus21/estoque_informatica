package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Produto;

import java.util.Optional;

public interface ProdutoService extends GenericService<Produto, Long> {

    Optional<Produto> findByEan(String ean);
}
