package com.example.infosystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateUserDto {
    private Long userId;
    private String login;
}
