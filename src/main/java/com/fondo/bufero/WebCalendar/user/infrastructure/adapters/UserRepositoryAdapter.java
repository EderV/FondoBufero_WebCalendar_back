package com.fondo.bufero.WebCalendar.user.infrastructure.adapters;

import com.fondo.bufero.WebCalendar.user.domain.User;
import com.fondo.bufero.WebCalendar.user.domain.out.UserRepositoryPort;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.entity.UserEntity;
import com.fondo.bufero.WebCalendar.user.infrastructure.mappers.UserMapper;
import com.fondo.bufero.WebCalendar.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public User getUserFromUsername(String username) {
        var userEntity = userRepository.getUserByUsername(username);
        return userEntity.map(this::toUser).orElse(null);
    }

    private User toUser(UserEntity userEntity) {
        return UserMapper.MAPPER.toUser(userEntity);
    }

}
