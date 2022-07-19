package com.example.infosystem.controllers;

import com.example.infosystem.converter.PlaylistConverter;
import com.example.infosystem.converter.TrackConverter;
import com.example.infosystem.dto.MessageResponse;
import com.example.infosystem.dto.PlaylistDto;
import com.example.infosystem.dto.PlaylistIdDto;
import com.example.infosystem.dto.TrackDto;
import com.example.infosystem.entity.Playlist;
import com.example.infosystem.entity.Track;
import com.example.infosystem.entity.User;
import com.example.infosystem.repos.PlaylistRepo;
import com.example.infosystem.repos.TrackRepo;
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
    TrackRepo trackRepo;

    @Autowired
    PlaylistRepo playlistRepo;

    @GetMapping("/myPlaylist")
    public Set<PlaylistDto> getUserPlaylists() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).
                orElseThrow(() -> new Exception("Пользователь не найден"));
        return PlaylistConverter.toDtos(user.getPlaylist());
    }

    @PutMapping("/myPlaylist/{playlistId}")
    public ResponseEntity<MessageResponse> updatePlaylist(@PathVariable Long playlistId,
                                                          @RequestBody PlaylistDto playlistDto) throws Exception {
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId).
                orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistRepo.save(PlaylistConverter.toEntity(playlistDto));
        return ResponseEntity.ok(new MessageResponse("Playlist UPDATED"));
    }

    @PutMapping("/myPlaylist/{playlistId}/newTrack")
    public ResponseEntity<MessageResponse> createNewTrackInPlaylist(@PathVariable Long playlistId,
                                                                    @RequestBody TrackDto trackDto) throws Exception {
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId).
                orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        trackRepo.save(TrackConverter.toEntity(trackDto));
        playlistService.createNewTrackInPlaylist(playlistId, trackDto.getTrackId());
        return ResponseEntity.ok(new MessageResponse("New Track in Playlist CREATED"));
    }

    @DeleteMapping("/myPlaylist/{playlistId}/deleteTrack/{trackId}")
    public ResponseEntity<MessageResponse> deleteTrackFromPlaylist(@PathVariable Long playlistId,
                                                                    @PathVariable Long trackId) throws Exception {
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId).
                orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistService.deleteTrackFromPlaylist(playlistId, trackId);
        return ResponseEntity.ok(new MessageResponse("Track Deleted from Playlist"));
    }

    @PutMapping("/myPlaylist/set")
    public ResponseEntity<MessageResponse> setExistedPlaylistToUser(@RequestBody PlaylistIdDto playlistId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).
                orElseThrow(() -> new Exception("Пользователь не найден"));
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId.getPlaylistId()).
                orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistService.updateUserPlaylistTable(user.getUserId(), playlist.getPlaylistId());
        return ResponseEntity.ok(new MessageResponse("User playlists UPDATED"));
    }


    @DeleteMapping("/myPlaylist/{playlistId}")
    public ResponseEntity<MessageResponse> deleteExistedPlaylistFromUser(@PathVariable Long playlistId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).
                orElseThrow(() -> new Exception("Пользователь не найден"));
        Playlist playlist = playlistRepo.getPlaylistByPlaylistId(playlistId).
                orElseThrow(() -> new RuntimeException("Плейлист не найден"));
        playlistRepo.deletePlaylistByPlaylistId(playlistId, user.getUserId());
        return ResponseEntity.ok(new MessageResponse("Playlist DELETED from user"));
    }

    @PutMapping("/new")
    public ResponseEntity<MessageResponse> createNewPlaylistToUser(@RequestBody PlaylistDto playlistDto) throws Exception {
        if(playlistRepo.existsByPlaylistId(playlistDto.getPlaylistId())){
            return ResponseEntity.
                    badRequest().
                    body(new MessageResponse("Плейлист с таким id уже существует"));
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByLogin(authentication.getName()).
                orElseThrow(() -> new Exception("Пользователь не найден"));
        playlistRepo.save(PlaylistConverter.toEntity(playlistDto));
        playlistService.updateUserPlaylistTable(user.getUserId(), playlistDto.getPlaylistId());
        return ResponseEntity.ok(new MessageResponse("Playlist CREATED AND ADDED TO USER"));
    }
}
