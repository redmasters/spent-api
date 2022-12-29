package io.red.spent.controllers.exceptions.handlers;

import io.red.spent.controllers.exceptions.ExpenseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.Instant;

@ControllerAdvice
public class CustomExceptionHandler {
    static final String DEFAULT_ERROR_VIEW = "error";
    static final String DEFAULT_ERROR_MESSAGE = "Unexpected error";

    @ExceptionHandler(value = ExpenseException.class)
    public ResponseEntity<DefaultError> entityNotFound(ExpenseException e, HttpServletRequest request) {
        int status = HttpStatus.NOT_FOUND.value();
        final var defaultError = new DefaultError(
                Instant.now(),
                status,
                DEFAULT_ERROR_VIEW,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(defaultError);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<DefaultError> constraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        int status = HttpStatus.BAD_REQUEST.value();
        final var defaultError = new DefaultError(
                Instant.now(),
                status,
                DEFAULT_ERROR_VIEW,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(defaultError);
    }

}
