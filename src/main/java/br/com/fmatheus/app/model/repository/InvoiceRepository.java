package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Invoice;
import br.com.fmatheus.app.model.repository.query.InvoiceRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>, InvoiceRepositoryQuery {

    Optional<Invoice> findByAccessKey(String accessKey);

    Collection<Invoice> findByNumber(String number);

    Optional<Invoice> findByInvoiceItems_SerialNumber(String serialNumber);
}
