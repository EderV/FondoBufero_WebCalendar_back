package com.fondo.bufero.WebCalendar.user.domain.component;

import com.fondo.bufero.WebCalendar.user.domain.Token;
import org.springframework.security.core.Authentication;

public interface TokenGenerator {

    Token createToken(Authentication authentication);

}
