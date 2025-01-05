package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


}
