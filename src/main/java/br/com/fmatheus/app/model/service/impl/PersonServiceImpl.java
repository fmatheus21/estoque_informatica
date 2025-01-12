package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.repository.PersonRepository;
import br.com.fmatheus.app.model.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<Person> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person save(Person person) {
        return this.repository.save(person);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
