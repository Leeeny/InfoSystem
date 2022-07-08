package com.example.infosystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    public JWTResponse(String token, Long id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }
}
