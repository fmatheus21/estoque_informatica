package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Pessoa;
import br.com.fmatheus.app.model.repository.PessoaRepository;
import br.com.fmatheus.app.model.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    @Override
    public List<Pessoa> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Pessoa> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return this.repository.save(pessoa);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
