package com.example.infosystem.repos;

import com.example.infosystem.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PlaylistRepo extends JpaRepository<Playlist, Long> {

    public Optional<Playlist> getPlaylistByPlaylistId(Long playlistId);

    @Transactional
    @Modifying
    @Query(value = "delete from user_playlist p where p.playlist_id=?1 and p.user_id =?2", nativeQuery = true)
    void deletePlaylistByPlaylistId(Long playlistId, Long userId);
}
