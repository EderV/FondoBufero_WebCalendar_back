package com.fondo.bufero.WebCalendar.user.infrastructure.security;

import com.fondo.bufero.WebCalendar.user.domain.in.JwtServicePort;
import com.fondo.bufero.WebCalendar.user.infrastructure.factory.RequestMatcherFactory;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${endpoints.publicAuth}")
    private String publicAuthEndpoint;

    @Value("${endpoints.publicEvent}")
    private String publicEventEndpoint;

    private final JwtServicePort jwtServicePort;
    private final UserDetailsService userDetailsService;
    private final RequestMatcherFactory requestMatcherFactory;

    private final RequestMatcher publicAuthEndpointMatcher = requestMatcherFactory.getRequestMatcher(publicAuthEndpoint);
    private final RequestMatcher publicEventEndpointMatcher = requestMatcherFactory.getRequestMatcher(publicEventEndpoint);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!publicAuthEndpointMatcher.matches(request) &&
            !publicEventEndpointMatcher.matches(request)
        ) {
            var jwt = extractJwtFromHeader(request);

            if (isJwtValid(jwt)) {
                var user = loadUserFromRepository(jwt);
                setAuthenticationInSecurityContext(user, request);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromHeader(HttpServletRequest request) throws ServletException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new ServletException("Incorrect authorization header");
    }

    private boolean isJwtValid(String jwt) throws ServletException {
        try {
            return jwtServicePort.validateAccessToken(jwt);
        } catch (SignatureException e) {
            throw new ServletException("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new ServletException("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new ServletException("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new ServletException("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ServletException("JWT claims string is empty: " + e.getMessage());
        }
    }

    private UserDetails loadUserFromRepository(String jwt) throws ServletException {
        try {
            var username = jwtServicePort.extractUsernameFromAccessToken(jwt);
            return userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ServletException("Username extracted from JWT not found in the database");
        }
    }

    private void setAuthenticationInSecurityContext(UserDetails userDetails, HttpServletRequest request) {
        var authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
