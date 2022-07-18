package com.example.infosystem.dto;

import com.example.infosystem.entity.Track;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    private Long playlistId;
    private String name;
    private Set<Track> tracks;
}
