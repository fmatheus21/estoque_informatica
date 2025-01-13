package br.com.fmatheus.app.controller.exception;


import java.io.Serial;

public class JsonConverterException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public JsonConverterException(String message) {
        super(message);
    }

}
