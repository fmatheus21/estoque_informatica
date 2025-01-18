package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.model.entity.Supplier;
import br.com.fmatheus.app.model.repository.SupplierRepository;
import br.com.fmatheus.app.model.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    @Override
    public List<Supplier> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Supplier> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Supplier> findByDocument(String documento) {
        return this.repository.findByPerson_Document(CharacterUtil.removeSpecialCharacters(documento));
    }
}
