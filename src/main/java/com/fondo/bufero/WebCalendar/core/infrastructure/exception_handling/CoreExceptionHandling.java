package com.fondo.bufero.WebCalendar.core.infrastructure.exception_handling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class CoreExceptionHandling {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Exception caught by global @ControllerAdvice. Message: ".concat(ex.getMessage()));
        log.error("STACK TRACE: ".concat(Arrays.toString(ex.getStackTrace())));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
