package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.model.entity.Produto;
import br.com.fmatheus.app.model.repository.ProdutoRepository;
import br.com.fmatheus.app.model.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    @Override
    public Optional<Produto> findByEan(String ean) {
        return this.repository.findByEan(CharacterUtil.removeAllSpaces(ean));
    }

    @Override
    public List<Produto> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Produto save(Produto produto) {
        return this.repository.save(produto);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

}
