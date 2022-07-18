package com.example.infosystem.converter;

import com.example.infosystem.dto.PlaylistDto;
import com.example.infosystem.dto.UserDto;
import com.example.infosystem.entity.Playlist;
import com.example.infosystem.entity.User;

import java.util.HashSet;
import java.util.Set;

public class PlaylistConverter {
    public static PlaylistDto toDto(Playlist playlist){
        return PlaylistDto.builder().
                playlistId(playlist.getPlaylistId()).
                name(playlist.getName()).
                tracks(playlist.getTracks()).
                build();
    }

    public static Playlist toEntity(PlaylistDto playlistDto) {
        return Playlist.builder().
                playlistId(playlistDto.getPlaylistId()).
                name(playlistDto.getName()).
                tracks(playlistDto.getTracks())
                .build();
    }
    
    public static Set<PlaylistDto> toDtos(Set<Playlist> playlists){
        Set<PlaylistDto> playlistDtos = new HashSet<>();
        for (Playlist p : playlists
             ) {
            playlistDtos.add(toDto(p));
        }
        return playlistDtos;
    }
    public static Set<Playlist> toEntityes(Set<PlaylistDto> playlistDtos){
        Set<Playlist> playlists = new HashSet<>();
        for (PlaylistDto p : playlistDtos
        ) {
            playlists.add(toEntity(p));
        }
        return playlists;
    }
}
