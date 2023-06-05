package com.fondo.bufero.WebCalendar.user.infrastructure.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {

    private UUID userId;
    private String accessToken;

}
