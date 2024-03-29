package com.example.infosystem.services;

import com.example.infosystem.dto.UserDto;
import com.example.infosystem.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    User getUser(Long id);

    Object addUser(UserDto userDto) throws Exception;

}
