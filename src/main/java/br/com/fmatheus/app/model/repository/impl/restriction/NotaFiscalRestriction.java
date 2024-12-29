package br.com.fmatheus.app.model.repository.impl.restriction;

import br.com.fmatheus.app.controller.dto.filter.NotaFiscalFilter;
import br.com.fmatheus.app.model.entity.Fornecedor;
import br.com.fmatheus.app.model.entity.NotaFiscal;
import br.com.fmatheus.app.model.entity.Pessoa;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.fmatheus.app.controller.util.CharacterUtil.removeDuplicateSpace;
import static br.com.fmatheus.app.controller.util.CharacterUtil.removeSpecialCharacters;

public abstract class NotaFiscalRestriction {

    private static final String PERCENT = "%";

    protected Predicate[] createRestrictions(NotaFiscalFilter filter, CriteriaBuilder builder, Root<NotaFiscal> root) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Fornecedor, NotaFiscal> joinFernecedor = root.join("fornecedor");
        Join<Pessoa, Fornecedor> joinPessoa = joinFernecedor.join("pessoa");

        if (Objects.nonNull(filter.chaveAcesso())) {
            predicates.add(builder.like(joinPessoa.get("chaveAcesso"), PERCENT + filter.chaveAcesso() + PERCENT));
        }

        if (Objects.nonNull(filter.numeroNotaFiscal())) {
            predicates.add(builder.like(joinPessoa.get("numeroNotaFiscal"), PERCENT + removeSpecialCharacters(filter.numeroNotaFiscal()) + PERCENT));
        }

        if (Objects.nonNull(filter.nomeFornecedor())) {
            predicates.add(builder.like(builder.lower(joinPessoa.get("nome")), PERCENT + removeDuplicateSpace(filter.nomeFornecedor().toLowerCase()) + PERCENT));
        }

        if (Objects.nonNull(filter.documentoFornecedor())) {
            predicates.add(builder.like(builder.lower(joinPessoa.get("documento")), PERCENT + removeSpecialCharacters(filter.documentoFornecedor()) + PERCENT));
        }

        return predicates.toArray(new Predicate[0]);

    }


    /**
     * Metodo responsavel por criar paginacao.
     *
     * @param typedQuery - TypedQuery
     * @param pageable   - Pageable
     * @author Fernando Matheus
     */
    protected void addPageRestrictions(TypedQuery<NotaFiscal> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;
        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

}
