package com.fondo.bufero.WebCalendar.user.infrastructure.mappers;

import com.fondo.bufero.WebCalendar.user.domain.Role;
import com.fondo.bufero.WebCalendar.user.domain.User;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.entity.RoleEntity;
import com.fondo.bufero.WebCalendar.user.infrastructure.dto.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", expression = "java(mapRoles(userEntity))")
    User toUser(UserEntity userEntity);
    @Mapping(target = "roles", expression = "java(mapRoleEntities(user))")
    UserEntity toUserEntity(User user);

    default List<Role> mapRoles(UserEntity userEntity) {
        var roles = new ArrayList<Role>();
        for (var roleEntity : userEntity.getRoles()) {
            var role = new Role();
            role.setRole(roleEntity.getRole());
            role.setEnabled(roleEntity.getEnabled());
            roles.add(role);
        }
        return roles;
    }

    default List<RoleEntity> mapRoleEntities(User user) {
        var roleEntities = new ArrayList<RoleEntity>();
        for (var role : user.getRoles()) {
            var roleEntity = new RoleEntity();
            roleEntity.setRole(role.getRole());
            roleEntity.setEnabled(role.getEnabled());
            roleEntities.add(roleEntity);
        }
        return roleEntities;
    }

//    @Mapping(target = "roles", ignore = true)
//    User toUserIgnoreRoles(UserEntity userEntity);
//    @Mapping(target = "roles", ignore = true)
//    UserEntity toUserEntityIgnoreRoles(User user);

//    default User toUser(UserEntity userEntity) {
//        var user = toUserIgnoreRoles(userEntity);
//
//        var roles = new ArrayList<Role>();
//        for (var roleEntity : userEntity.getRoles()) {
//            var role = new Role();
//            role.setRole(roleEntity.getRole());
//            role.setEnabled(roleEntity.getEnabled());
//            roles.add(role);
//        }
//        user.setRoles(roles);
//
//        return user;
//    }

//    default UserEntity toUserEntity(User user) {
//        var userEntity = toUserEntityIgnoreRoles(user);
//
//        var roleEntities = new ArrayList<RoleEntity>();
//        for (var role : user.getRoles()) {
//            var roleEntity = new RoleEntity();
//            roleEntity.setRole(role.getRole());
//            roleEntity.setEnabled(role.getEnabled());
//            roleEntities.add(roleEntity);
//        }
//        userEntity.setRoles(roleEntities);
//
//        return userEntity;
//    }

}
