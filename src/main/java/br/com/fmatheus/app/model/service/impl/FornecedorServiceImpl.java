package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.model.entity.Fornecedor;
import br.com.fmatheus.app.model.repository.FornecedorRepository;
import br.com.fmatheus.app.model.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository repository;

    @Override
    public List<Fornecedor> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Fornecedor> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return this.repository.save(fornecedor);
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Fornecedor> findByDocument(String documento) {
        return this.repository.findByPessoa_Documento(CharacterUtil.removeSpecialCharacters(documento));
    }
}
