package overengineer.projecthttp.presentation.api.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import overengineer.projecthttp.presentation.api.exception.ApiException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException err) {
        log.error("Internal server error", err);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseError(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now()));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> handleApiException(ApiException err) {
        return ResponseEntity.status(err.getStatusCode())
                .body(new ResponseError(err.getMessage(), err.getStatusCode(), LocalDateTime.now()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        var responseError = new ResponseError(errors.toString(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(responseError, new HttpHeaders(), responseError.status());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        Class<?> requiredType = ex.getRequiredType();
        if (Objects.isNull(requiredType)) {
            return ResponseEntity.internalServerError()
                .body(new ResponseError("Required type is null",
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        LocalDateTime.now()));
        }
        String error = ex.getName() + " should be of type " + requiredType.getName();
        var responseError = new ResponseError(error, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(responseError, new HttpHeaders(), responseError.status());
    }

}
