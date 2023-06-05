package com.fondo.bufero.WebCalendar.user.application;

import com.fondo.bufero.WebCalendar.user.domain.Credentials;
import com.fondo.bufero.WebCalendar.user.domain.in.AuthCheckerPort;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthCheckerDefault implements AuthCheckerPort {

    // TODO Implement unit/functional tests

    @Override
    public void checkCredentials(Credentials credentials) throws IllegalArgumentException {
        nullCheck(credentials);
        checkMinimumDataRequired(credentials);
        checkIllegalCharacters(credentials);
    }

    private void nullCheck(Object object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException("Input data is null");
        }
    }

    private void checkMinimumDataRequired(Credentials credentials) throws IllegalArgumentException {
        if (credentials.getUsername() == null || credentials.getUsername().isBlank() ||
            credentials.getPassword() == null || credentials.getUsername().isBlank()
        ) {
            throw new IllegalArgumentException(
                    "Data provided in Credentials is not minimum required and/or is not correct");
        }
    }

    private void checkIllegalCharacters(Credentials credentials) throws IllegalArgumentException {
        if (findIllegalCharacters(credentials.getUsername())) {
            throw new IllegalArgumentException("Data provided contains illegal characters");
        }
    }

    private boolean findIllegalCharacters(String input) {
        if (input != null) {
            var pattern = "[<>\"';]";
            var regex = Pattern.compile(pattern);
            var matcher = regex.matcher(input);
            return matcher.find();
        }
        return false;
    }

}
