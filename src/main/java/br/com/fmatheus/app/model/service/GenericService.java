package br.com.fmatheus.app.model.service;

import java.util.Collection;
import java.util.Optional;

public interface GenericService<T, ID> {

    Collection<T> findAll();

    Optional<T> findById(ID id);

    T save(T t);

    void deleteById(ID id);
}
