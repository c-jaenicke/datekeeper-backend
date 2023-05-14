package datekeeper.datekeeperbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle Exceptions for RestController
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Illegal argument used")
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleException(IllegalArgumentException e) {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Supplied value was not a parseable long")
    @ExceptionHandler(NumberFormatException.class)
    public void handleException(NumberFormatException e) {
    }
}
