package com.fondo.bufero.WebCalendar.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    private UUID id;

    private User user;

    private String role;

    private Boolean enabled;

    private Date createdAt;

    @Override
    public String getAuthority() {
        return role;
    }

}
