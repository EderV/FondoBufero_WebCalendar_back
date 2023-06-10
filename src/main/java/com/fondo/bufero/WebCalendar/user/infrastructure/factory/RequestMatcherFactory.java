package com.fondo.bufero.WebCalendar.user.infrastructure.factory;

import org.springframework.security.web.util.matcher.RequestMatcher;

public interface RequestMatcherFactory {

    RequestMatcher getRequestMatcher(String matcher);

}
