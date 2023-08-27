package com.fondo.bufero.WebCalendar.event.infrastructure.exception_handling;

import com.fondo.bufero.WebCalendar.event.domain.exceptions.EventNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class EventExceptionHandling {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private ResponseEntity<String> handleIllegalArgumentException(EventNotFoundException ex) {
        log.error("EventNotFoundException handled by @ControllerAdvice".concat(ex.getMessage()));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
    }

}
