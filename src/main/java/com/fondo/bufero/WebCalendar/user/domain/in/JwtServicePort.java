package com.fondo.bufero.WebCalendar.user.domain.in;

import com.fondo.bufero.WebCalendar.user.domain.Token;
import org.springframework.security.core.Authentication;

public interface JwtServicePort {

    Token getAccessToken(Authentication authentication);

    String extractUsernameFromAccessToken(String token);

    boolean validateAccessToken(String token);

}
