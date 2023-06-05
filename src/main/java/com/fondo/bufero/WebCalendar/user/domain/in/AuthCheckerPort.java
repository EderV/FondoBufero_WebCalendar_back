package com.fondo.bufero.WebCalendar.user.domain.in;

import com.fondo.bufero.WebCalendar.user.domain.Credentials;

public interface AuthCheckerPort {

    void checkCredentials(Credentials credentials) throws IllegalArgumentException;

}
