package ru.job4j.exception;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.job4j.exception.model.AnswerError;

@ControllerAdvice
public class GlobalPSQLException {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalPSQLException.class);

    @ExceptionHandler(value = {PSQLException.class})
    public ResponseEntity<AnswerError> handleException(Exception e, WebRequest request) {
        LOG.error(e.getMessage());
        return new ResponseEntity<>(
                new AnswerError("Passport series and number are not unique!"), HttpStatus.BAD_REQUEST
        );
    }
}
