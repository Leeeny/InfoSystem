package com.example.infosystem.dto;

import com.example.infosystem.entity.Album;
import com.example.infosystem.entity.Style;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TrackDto {
    private Long trackId;
    private String name;
    private String artist;
    private Long time;
    private Set<Style> styles;
    private Album albums;
}
