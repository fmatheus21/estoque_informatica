package br.com.fmatheus.app.controller.enumerable;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MessagesEnum {
    SUCCESS_CREATE(HttpStatus.CREATED, "message.success.create"),
    SUCCESS_UPDATE(HttpStatus.CREATED, "message.success.update"),
    SUCCESS_DELETE(HttpStatus.OK, "message.success.delete"),
    ERROR_INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "message.error.internal-server"),
    ERROR_FORBIDDEN(HttpStatus.FORBIDDEN, "message.error.forbidden"),
    ERROR_BAD_REQUEST(HttpStatus.BAD_REQUEST, "message.error.bad-request"),
    ERROR_NOT_PERMISSION(HttpStatus.FORBIDDEN, "message.error.not-permission"),
    ERROR_NOT_FOUND(HttpStatus.BAD_REQUEST, "message.error.not-found"),
    ERROR_NOT_READABLE(HttpStatus.BAD_REQUEST, "message.error.not-readable"),
    ERROR_NOT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "message.error.unauthorized"),
    ERROR_EXIST_RECORD(HttpStatus.BAD_REQUEST, "message.error.exist-record"),
    ERROR_EXIST_DOCUMENT(HttpStatus.BAD_REQUEST, "message.error.exist-document"),
    ERROR_RECORD_NOT_EXIST(HttpStatus.BAD_REQUEST, "message.error.record-not-exist"),
    ERROR_DATA_INTEGRITY_VIOLATION(HttpStatus.NOT_ACCEPTABLE, "message.error.data-integrity-violation"),
    ERROR_USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "message.error.user-not-exist"),
    ERROR_PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "message.error.password-not-match");


    private final HttpStatus httpSttus;
    private final String message;

    MessagesEnum(HttpStatus httpSttus, String message) {
        this.httpSttus = httpSttus;
        this.message = message;
    }

}
