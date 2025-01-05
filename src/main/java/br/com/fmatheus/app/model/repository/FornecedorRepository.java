package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Fornecedor;
import br.com.fmatheus.app.model.repository.query.NotaFiscalRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

    Optional<Fornecedor> findByPessoa_Documento(String documento);

}
