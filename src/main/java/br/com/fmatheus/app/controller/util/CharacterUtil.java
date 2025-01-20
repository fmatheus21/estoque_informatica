package br.com.fmatheus.app.controller.util;


import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;

import java.util.Objects;

public class CharacterUtil extends CapitalizeUtil {

    private CharacterUtil() {
        super();
    }

    /**
     * Converte o primeiro caracter de cada palavra em maiusculo.
     *
     * @param value String
     * @return String
     * @author Fernando Matheus
     */
    public static String convertFirstUppercaseCharacter(String value) {
        return Objects.nonNull(value) ? capitalizeFully(removeDuplicateSpace(value)).trim() : null;
    }

    /**
     * Converter todos caracteres de uma string em maiusculo.
     *
     * @param value String
     * @return String
     * @author Fernando Matheus
     */
    public static String convertAllUppercaseCharacters(String value) {
        return Objects.nonNull(value) ? value.toUpperCase().trim() : null;
    }

    /**
     * Converter todos caracteres de uma string em minusculo.
     *
     * @param value String
     * @return String
     * @author Fernando Matheus
     */
    public static String convertAllLowercaseCharacters(String value) {
        return Objects.nonNull(value) ? value.toLowerCase().trim() : null;
    }

    /**
     * Remove todos os espacos de uma string.
     *
     * @param value String
     * @return String
     * @author Fernando Matheus
     */
    public static String removeAllSpaces(String value) {
        return Objects.nonNull(value) ? value.replace(" ", "") : null;
    }

    /**
     * Remove duplicidade de espaco em uma string.
     *
     * @param value String
     * @return String
     * @author Fernando Matheus
     */
    public static String removeDuplicateSpace(String value) {
        return Objects.nonNull(value) ? value.replaceAll("\\s+", " ").trim() : null;
    }


    public static String removeSpecialCharacters(String value) {
        return Objects.nonNull(value) ? value.replaceAll("[^a-zA-Z0-9]", "") : null;
    }

    public static String formatMask(String valor, String mascara) {

        StringBuilder dado = new StringBuilder();
        for (var i = 0; i < valor.length(); i++) {
            var c = valor.charAt(i);
            if (Character.isDigit(c)) {
                dado.append(c);
            }
        }
        int indMascara = mascara.length();
        int indCampo = dado.length();

        while (indCampo > 0 && indMascara > 0) {
            if (mascara.charAt(--indMascara) == '#') {
                indCampo--;
            }
        }

        StringBuilder saida = new StringBuilder();
        for (; indMascara < mascara.length(); indMascara++) {
            saida.append((mascara.charAt(indMascara) == '#') ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
        }
        return saida.toString();
    }

    public static String formatCPF(String value) {
        return Objects.nonNull(value) ? new CPFFormatter().format(value) : null;
    }


    public static String formatCNPJ(String value) {
        return Objects.nonNull(value) ? new CNPJFormatter().format(value) : null;
    }


}
