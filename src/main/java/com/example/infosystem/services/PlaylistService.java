package com.example.infosystem.services;

import com.example.infosystem.dto.TrackDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface PlaylistService {
    void updateUserPlaylistTable(Long userId, Long playlistId);

    void createNewTrackInPlaylist(Long playlistId, Long trackId);

    void deleteTrackFromPlaylist(Long playlistId, Long trackId);
}
