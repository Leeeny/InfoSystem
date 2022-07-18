package com.example.infosystem.controllers;

import com.example.infosystem.entity.User;
import com.example.infosystem.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/homepage")
public class HomepageController {

    @Autowired
    private final UserRepo userRepo;

    @GetMapping
    public Optional<User> getHomepage() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(userRepo.findUserByLogin(authentication.getName()).orElseThrow(() -> new Exception("Пользователь не найден")));
    }

}
