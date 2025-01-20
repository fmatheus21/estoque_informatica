package br.com.fmatheus.app.controller.exception.handler;


import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.JasperException;
import br.com.fmatheus.app.controller.exception.JsonConverterException;
import br.com.fmatheus.app.controller.exception.XmlConverterException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String UNEXPECTED_ERROR = "Ocorreu um erro inesperado.";
    private static final String MESSAGE = "massage";
    private final MessageSource messageSource;

    @Override
    protected @NonNull ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, "O corpo da requisição está inválido ou mal formatado.");
        problemDetail.setProperty("message", ex.getMostSpecificCause().getMessage());
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    /**
     * Tratamento de validacao de argumentos
     *
     * @param ex      Excecao lancada
     * @param headers HttpHeaders
     * @param status  HttpStatusCode
     * @param request WebRequest
     * @return {@link Object}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("Campo '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        var httpStatus = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, "Erro de validação nos campos fornecidos.");
        problemDetail.setProperty("errors", errors);
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    /**
     * Excecao de tipo invalido nos argumentos.
     *
     * @param ex Excecao lancada
     * @return {@link ProblemDetail}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, "Tipo de argumento inválido.");
        problemDetail.setProperty("parameter", ex.getName());
        problemDetail.setProperty("expectedType", ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido");
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    /**
     * Tratamento generico para excecoes nao mapeadas.
     *
     * @param ex Excecao lancada
     * @return {@link ProblemDetail}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, UNEXPECTED_ERROR);
        problemDetail.setProperty(MESSAGE, ex.getMessage());
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    /**
     * Tratamento de erros BadRequestException.
     *
     * @param ex Excecao lancada
     * @return {@link ProblemDetail}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequestException(Exception ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, UNEXPECTED_ERROR);
        problemDetail.setProperty(MESSAGE, this.returnMessage(ex));
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler({XmlConverterException.class, JsonConverterException.class, JasperException.class})
    public ResponseEntity<ProblemDetail> handleConverterException(Exception ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, UNEXPECTED_ERROR);
        problemDetail.setProperty(MESSAGE, this.returnMessage(ex));
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    private String returnMessage(Exception ex) {
        try {
            return this.messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return ex.getMessage();
        }
    }

}