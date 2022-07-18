package com.example.infosystem.controllers;

import com.example.infosystem.converter.PlaylistConverter;
import com.example.infosystem.dto.MessageResponse;
import com.example.infosystem.dto.PlaylistDto;
import com.example.infosystem.dto.PlaylistIdDto;
import com.example.infosystem.entity.Playlist;
import com.example.infosystem.entity.User;
import com.example.infosystem.repos.PlaylistRepo;
import com.example.infosystem.repos.UserRepo;
import com.example.infosystem.services.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    PlaylistRepo playlistRepo;

    @GetMapping("/myPlaylist")
    public Set<PlaylistDto> getUserPlaylists() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).orElseThrow(() -> new Exception("Пользователь не найден"));
        return PlaylistConverter.toDtos(user.getPlaylist());
    }

    @PutMapping("/myPlaylist/{playlistId}")
    public ResponseEntity<MessageResponse> updatePlaylist(@PathVariable Long playlistId,
                                                          @RequestBody PlaylistDto playlistDto) throws Exception {
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId).orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistRepo.save(PlaylistConverter.toEntity(playlistDto));
        return ResponseEntity.ok(new MessageResponse("Playlist UPDATED"));
    }

    @PutMapping("/myPlaylist/set")
    public ResponseEntity<MessageResponse> setExistedPlaylistToUser(@RequestBody PlaylistIdDto playlistId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).orElseThrow(() -> new Exception("Пользователь не найден"));
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId.getPlaylistId()).orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistService.updateUserPlaylistTable(user.getUserId(), playlist.getPlaylistId());
        return ResponseEntity.ok(new MessageResponse("User playlists UPDATED"));
    }

    @DeleteMapping("/myPlaylist/{playlistId}")
    public ResponseEntity<MessageResponse> deleteExistedPlaylistFromUser(@PathVariable Long playlistId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).orElseThrow(() -> new Exception("Пользователь не найден"));
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId).orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistRepo.deletePlaylistByPlaylistId(playlistId, user.getUserId());
        return ResponseEntity.ok(new MessageResponse("Playlist DELETED from user"));
    }

}
