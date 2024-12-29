package br.com.fmatheus.app.controller.util;


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


}
