package com.example.infosystem.services.implementations;

import com.example.infosystem.dto.TrackDto;
import com.example.infosystem.services.PlaylistService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public void updateUserPlaylistTable(Long userId, Long playlistId) {
        entityManager.createNativeQuery("insert into user_playlist (user_id, playlist_id) values (?, ?)").
                setParameter(1, userId).
                setParameter(2, playlistId).
                executeUpdate();
    }

    @Override
    @Transactional
    public void createNewTrackInPlaylist(Long playlistId, Long trackId) {
        entityManager.createNativeQuery("insert into playlist_track (playlist_id, track_id) values (?, ?)").
                setParameter(1, playlistId).
                setParameter(2, trackId).
                executeUpdate();
    }

    @Override
    @Transactional
    public void deleteTrackFromPlaylist(Long playlistId, Long trackId) {
        entityManager.createNativeQuery("delete from playlist_track pt where pt.playlist_id = ? and pt.track_id = ?").
                setParameter(1, playlistId).
                setParameter(2, trackId).
                executeUpdate();
    }


}
