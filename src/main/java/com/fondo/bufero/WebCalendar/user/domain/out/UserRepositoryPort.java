package com.fondo.bufero.WebCalendar.user.domain.out;

import com.fondo.bufero.WebCalendar.user.domain.User;

public interface UserRepositoryPort {

    User getUserFromUsername(String username);

}
