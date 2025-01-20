package br.com.fmatheus.app.controller.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {


    private DateFormatterUtil() {
        throw new IllegalStateException(getClass().getName());
    }


    /**
     * Recebe uma data em String, adiciona formatacao e retorna uma data em String.
     *
     * @param date Data recebida em string
     * @return {@link String}
     */
    public static String converterDate(String date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ZonedDateTime.parse(date).format(desiredFormatter);
    }
}
