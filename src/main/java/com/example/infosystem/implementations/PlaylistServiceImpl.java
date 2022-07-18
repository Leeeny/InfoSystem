package com.example.infosystem.implementations;

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
        entityManager.createNativeQuery("insert into user_playlist (user_id, playlist_id) values (?, ?)")
                .setParameter(1, userId)
                .setParameter(2, playlistId)
                .executeUpdate();
    }
}
