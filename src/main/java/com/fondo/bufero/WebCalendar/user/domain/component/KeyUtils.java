package com.fondo.bufero.WebCalendar.user.domain.component;

import java.security.Key;

public interface KeyUtils {

    Key getAccessTokenPrivateKey();

    Key getAccessTokenPublicKey();

}
