package com.fondo.bufero.WebCalendar.user.application;

import com.fondo.bufero.WebCalendar.user.domain.Credentials;
import com.fondo.bufero.WebCalendar.user.domain.in.AuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceDefault implements AuthServicePort {

    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication login(Credentials credentials) throws AuthenticationException {
        var username = credentials.getUsername();
        var password = credentials.getPassword();

        var userDetails = User.builder().username(username).password(password).build();
        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        return authenticationProvider.authenticate(authentication);
    }

}
