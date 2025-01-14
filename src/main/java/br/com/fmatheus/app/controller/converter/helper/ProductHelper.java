package br.com.fmatheus.app.controller.converter.helper;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

import br.com.fmatheus.app.model.entity.Product;

public abstract class ProductHelper {

    /**
     * Processa alteracoes nos valores dos atributos antes de realizar o commit.
     * Exemplo: Converter os caracteres em maiusculos.
     *
     * @param product Entidade
     * @return {@link Product}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    protected Product input(Product product) {
        product.setName(convertAllUppercaseCharacters(product.getName()));
        return product;
    }

    /**
     * Processa alteracoes nos valores dos atributos antes de retornar a resposta.
     *
     * @param product Entidade
     * @return {@link Product}
     */
    protected Product output(Product product) {
        product.setName(convertFirstUppercaseCharacter(product.getName()));
        product.getManufacturer().setName(convertFirstUppercaseCharacter(product.getManufacturer().getName()));
        return product;
    }
}
