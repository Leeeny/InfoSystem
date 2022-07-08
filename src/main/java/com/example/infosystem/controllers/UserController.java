package com.example.infosystem.controllers;

import com.example.infosystem.dto.MessageResponse;
import com.example.infosystem.dto.UserDto;
import com.example.infosystem.entity.User;
import com.example.infosystem.jwt.JwtUtils;
import com.example.infosystem.repos.UserRepo;
import com.example.infosystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody UserDto userDto) throws Exception {
        if (userRepo.existsByLogin(userDto.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }
        User user = new User(userDto.getLogin(),
                passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
