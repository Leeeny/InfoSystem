package com.example.infosystem.services;

import com.example.infosystem.dto.UserDto;
import com.example.infosystem.entity.User;

import javax.transaction.Transactional;

public interface UserService {
    User getUser(Long id);

    Object addUser(UserDto userDto) throws Exception;

}
