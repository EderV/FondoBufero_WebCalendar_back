package com.fondo.bufero.WebCalendar.user.infrastructure.mappers;

import com.fondo.bufero.WebCalendar.user.domain.Token;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.request.TokenRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    TokenMapper MAPPER = Mappers.getMapper(TokenMapper.class);

    TokenRequest toTokenRequest(Token token);

}
