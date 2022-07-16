package com.example.infosystem.implementations;

import com.example.infosystem.converter.UserConverter;
import com.example.infosystem.dto.UserDto;
import com.example.infosystem.entity.User;
import com.example.infosystem.repos.UserRepo;
import com.example.infosystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public UserDto addUser(UserDto newUser) throws Exception {
        if(userRepo.existsByLogin(newUser.getLogin()))
            throw new Exception("Пользователь с данных именем уже существует");
        return UserConverter.toDto(userRepo.save(UserConverter.toEntity(newUser)));
    }
}
