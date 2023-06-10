package com.fondo.bufero.WebCalendar.user.domain.in;

import com.fondo.bufero.WebCalendar.user.domain.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthServicePort {

    Authentication login(Credentials credentials) throws AuthenticationException;

}
