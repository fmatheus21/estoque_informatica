package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.NotaFiscal;
import br.com.fmatheus.app.model.repository.query.NotaFiscalRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, UUID>, NotaFiscalRepositoryQuery {

    Optional<NotaFiscal> findByChaveAcesso(String chaveAcesso);

    Collection<NotaFiscal> findByNumero(String numero);

    Optional<NotaFiscal> findByNotaFiscalItems_SerialNumber(String serialNumber);
}
