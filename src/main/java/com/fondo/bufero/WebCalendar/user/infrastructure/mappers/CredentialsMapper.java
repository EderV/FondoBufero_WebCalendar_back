package com.fondo.bufero.WebCalendar.user.infrastructure.mappers;

import com.fondo.bufero.WebCalendar.user.domain.Credentials;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.request.CredentialsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    CredentialsMapper MAPPER = Mappers.getMapper(CredentialsMapper.class);

    Credentials toCredentials(CredentialsRequest credentialsRequest);

}
