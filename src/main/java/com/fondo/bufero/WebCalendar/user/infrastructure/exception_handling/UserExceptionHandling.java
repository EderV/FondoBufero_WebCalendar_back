package com.fondo.bufero.WebCalendar.user.infrastructure.exception_handling;

import com.fondo.bufero.WebCalendar.event.domain.exceptions.EventNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserExceptionHandling {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        log.error("AuthenticationException handled by User @ControllerAdvice. Message: ".concat(ex.getMessage()));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
