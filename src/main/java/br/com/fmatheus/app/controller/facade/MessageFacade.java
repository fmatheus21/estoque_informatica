package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.FileStorageException;
import br.com.fmatheus.app.controller.exception.JsonConverterException;
import br.com.fmatheus.app.controller.exception.XmlConverterException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageFacade {

    private static final String EXIST_RECORD = "message.error.exist-record";
    private static final String UNAUTHORIZED = "message.error.unauthorized";
    private static final String NOT_READABLE = "message.error.not-readable";
    private static final String INTERNAL_SERVER = "message.error.internal-server";
    private static final String FORBIDEN = "message.error.forbidden";
    private static final String BAD_REQUEST = "message.error.bad-request";
    private static final String NOT_PERMISSION = "message.error.not-permission";
    private static final String NOT_FOUND = "message.error.not-found";
    private static final String EXIST_DOCUMENT = "message.error.exist-document";
    private static final String RECORD_NOT_EXIST = "message.error.record-not-exist";
    private static final String DATA_INTEGRITY_VIOLATION = "message.error.data-integrity-violation";
    private static final String ACCESS_KEY_ALREADY = "message.error.access-key-already";
    private static final String SERIAL_NUMBER_ALREADY = "message.error.serial-number-already";
    private static final String XML_CONVERTER = "message.error.xml-converter";
    private static final String JSON_CONVERTER = "message.error.json-converter";
    private static final String FILE_STORAGE = "message.error.file-storage";
    private static final String SUCCESS_DELETE = "message.success.delete";
    private static final String SUCCESS_UPDATE = "message.success.update";
    private static final String SUCCESS_CREATE = "message.success.create";

    private final MessageSource messageSource;

    public BadRequestException errorAccessKeyAlready() {
        return new BadRequestException(ACCESS_KEY_ALREADY);
    }

    public XmlConverterException errorXmlConverter() {
        return new XmlConverterException(XML_CONVERTER);
    }

    public JsonConverterException errorJsonConverter() {
        return new JsonConverterException(JSON_CONVERTER);
    }

    public FileStorageException errorFileStorage() {
        return new FileStorageException(FILE_STORAGE);
    }

    public BadRequestException errorSerialNumberAlready(String serialNumber) {
        return new BadRequestException(String.format("O Número de Série %s já está cadastrado.", serialNumber));
    }

    public BadRequestException errorEanAlready(String ean) {
        return new BadRequestException(String.format("O EAN %s já está cadastrado.", ean));
    }
}
