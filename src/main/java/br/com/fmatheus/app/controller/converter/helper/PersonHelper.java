package br.com.fmatheus.app.controller.converter.helper;

import br.com.fmatheus.app.model.entity.Person;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

public abstract class PersonHelper {

    /**
     * Processa alteracoes nos valores dos atributos antes de realizar o commit.
     * Exemplo: Converter os caracteres em maiusculos.
     *
     * @param person Entidade
     * @return {@link Person}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    protected Person input(Person person) {
        person.setName(removeDuplicateSpace(convertAllUppercaseCharacters(person.getName())));
        person.setDocument(removeSpecialCharacters(person.getDocument()));
        person.getContact().setPhone(removeSpecialCharacters(person.getContact().getPhone()));
        return person;
    }

    /**
     * Processa alteracoes nos valores dos atributos antes de retornar a resposta.
     * Exemplo: Converter os caracteres em minusculos.
     *
     * @param person Entidade
     * @return {@link Person}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    protected Person output(Person person) {
        person.setName(convertFirstUppercaseCharacter(person.getName()));
        return person;
    }
}
