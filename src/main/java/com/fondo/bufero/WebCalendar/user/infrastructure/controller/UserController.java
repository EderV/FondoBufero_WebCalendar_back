package com.fondo.bufero.WebCalendar.user.infrastructure.controller;

import com.fondo.bufero.WebCalendar.user.domain.Credentials;
import com.fondo.bufero.WebCalendar.user.domain.Token;
import com.fondo.bufero.WebCalendar.user.domain.in.AuthCheckerPort;
import com.fondo.bufero.WebCalendar.user.domain.in.AuthServicePort;
import com.fondo.bufero.WebCalendar.user.domain.in.JwtServicePort;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.entity.RoleEntity;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.entity.UserEntity;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.request.CredentialsRequest;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.request.TokenRequest;
import com.fondo.bufero.WebCalendar.user.infrastructure.mappers.CredentialsMapper;
import com.fondo.bufero.WebCalendar.user.infrastructure.mappers.TokenMapper;
import com.fondo.bufero.WebCalendar.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthCheckerPort authCheckerPort;
    private final AuthServicePort authServicePort;
    private final JwtServicePort jwtServicePort;

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Value("${access-token.duration_minutes}")
    private int accessTokenDuration;

    @GetMapping
    public ResponseEntity<String> test() {
//        var user = UserEntity.builder()
//                .email("")
//                .username("TestNormalUser")
//                .password(encoder.encode("password_123098"))
//                .accountEnabled(true)
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .build();
//
//        var roleDefault = RoleEntity.builder()
//                .user(user)
//                .role("ROLE_USER")
//                .enabled(true)
//                .build();
//
//        var roles = Arrays.asList(roleDefault); // , roleUser);
//        user.setRoles(roles);
//
//        userRepository.save(user);

        return ResponseEntity.ok("");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsRequest credentialsRequest) {
        var credentials = toCredentials(credentialsRequest);

        try {
            authCheckerPort.checkCredentials(credentials);
            var authentication = authServicePort.login(credentials);
            var token = jwtServicePort.getAccessToken(authentication);
            var tokenRequest = toTokenRequest(token);

            tokenRequest.setSessionExpiration(accessTokenDuration);

            return new ResponseEntity<>(tokenRequest, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    private Credentials toCredentials(CredentialsRequest credentialsRequest) {
        return CredentialsMapper.MAPPER.toCredentials(credentialsRequest);
    }

    private TokenRequest toTokenRequest(Token token) {
        return TokenMapper.MAPPER.toTokenRequest(token);
    }

}
