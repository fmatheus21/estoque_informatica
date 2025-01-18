package br.com.fmatheus.app.controller.converter.helper;

import br.com.fmatheus.app.model.entity.Invoice;

import static br.com.fmatheus.app.controller.util.CharacterUtil.convertAllUppercaseCharacters;
import static br.com.fmatheus.app.controller.util.CharacterUtil.removeAllSpaces;

public abstract class InvoiceHelper {

    /**
     * Processa alteracoes nos valores dos atributos antes de realizar o commit.
     * Exemplo: Converter os caracteres em maiusculos.
     *
     * @param invoice Entidade
     * @return {@link Invoice}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    protected Invoice input(Invoice invoice) {
        invoice.setAccessKey(removeAllSpaces(invoice.getAccessKey()));
        invoice.getInvoiceItems().forEach(item -> item.setSerialNumber(convertAllUppercaseCharacters(item.getSerialNumber())));
        return invoice;
    }

    /**
     * Processa alteracoes nos valores dos atributos antes de retornar a resposta.
     * Exemplo: Converter os caracteres em minusculos.
     *
     * @param invoice Entidade
     * @return {@link Invoice}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    protected Invoice output(Invoice invoice) {
        return invoice;
    }
}
