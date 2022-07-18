package com.example.infosystem.controllers;

import com.example.infosystem.dto.DeleteUserDto;
import com.example.infosystem.dto.MessageResponse;
import com.example.infosystem.dto.UpdateUserDto;
import com.example.infosystem.entity.User;
import com.example.infosystem.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/settings")
public class UserSettingsController {

    @Autowired
    private final UserRepo userRepo;

    @PutMapping()
    public ResponseEntity<MessageResponse> updateUser(@RequestBody UpdateUserDto userDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).orElseThrow(() -> new Exception("Пользователь не найден"));
        if (userRepo.existsByLogin(userDto.getLogin()) && !Objects.equals(user.getUserId(), userDto.getUserId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }
        userRepo.updateUserByUserId(userDto.getUserId(), userDto.getLogin());
        //redirect on login page
        return ResponseEntity.ok(new MessageResponse("User UPDATED"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> deleteUser(@RequestBody DeleteUserDto userDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).orElseThrow(() -> new Exception("Пользователь не найден"));
        userRepo.deleteUserByUserId(userDto.getUserId());
        //redirect on login page
        return ResponseEntity.ok(new MessageResponse("User DELETED"));
    }

}
