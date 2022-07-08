package com.example.infosystem.converter;

import com.example.infosystem.dto.UserDto;
import com.example.infosystem.entity.User;

public class UserConverter {
    public static UserDto toDto(User user){
        return UserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto newUser) {
        return User.builder()
                .login(newUser.getLogin())
                .password(newUser.getPassword())
                .build();
    }
}
