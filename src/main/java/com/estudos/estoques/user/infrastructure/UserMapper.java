package com.estudos.estoques.user.infrastructure;

import com.estudos.estoques.user.domain.User;
import com.estudos.estoques.user.domain.UserEmail;
import com.estudos.estoques.user.domain.UserId;
import com.estudos.estoques.user.domain.UserName;
import com.estudos.estoques.user.domain.UserPasswordHash;
import com.estudos.estoques.user.domain.UserRole;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId().getValue());
        entity.setName(user.getName().getValue());
        entity.setEmail(user.getEmail().getValue());
        entity.setPassword(user.getPasswordHash().getValue());
        entity.setRole(user.getRole().name());
        return entity;
    }
    public static User toDomain(UserEntity entity) {
        User user = new User(
            new UserId(entity.getId()),
            new UserName(entity.getName()),
            new UserEmail(entity.getEmail()),
            new UserPasswordHash(entity.getPassword()),
            UserRole.valueOf(entity.getRole())
        );
        return user;
    }
}
