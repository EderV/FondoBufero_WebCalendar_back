package com.fondo.bufero.WebCalendar.user.domain;

import com.fondo.bufero.WebCalendar.user.infrastructure.dto.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private UUID id;

    private String email;

    private String username;

    private String password;

    private Boolean accountEnabled;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean credentialsExpired;

    private Date createdAt;

    private List<RoleEntity> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return accountEnabled;
    }

}
