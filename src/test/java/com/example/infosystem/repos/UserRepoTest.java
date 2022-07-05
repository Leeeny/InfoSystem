package com.example.infosystem.repos;

import com.example.infosystem.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StyleRepo styleRepo;

    @Autowired
    private TrackRepo trackRepo;

    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private AlbumRepo albumRepo;

    @Test
    public void registerNewUserTest() {
        User user = User.builder().userId(1L).login("loh").password("loh2").build();
        userRepo.save(user);
        System.out.println(user);
    }

    @Test
    public void newPlaylistTest() {
        Playlist playlist = Playlist.builder().playlistId(1L).name("playlistLoha").build();
        playlistRepo.save(playlist);
    }

    @Test
    public void newStyleTest(){
        Style style = Style.builder().styleId(1L).name("Rock").build();
        styleRepo.save(style);
    }

    @Test
    public void newTrackTestWithNulls(){
        Track track = Track.builder().trackId(1L).time(1000L).artist("LOOOH").name("Track").build();
        trackRepo.save(track);
    }

    @Test
    public void newAlbum(){
        Track track = Track.builder().trackId(2L).time(1200L).artist("sdf").name("Traafsdfck").build();
        trackRepo.save(track);
        Set<Track> tracks = new HashSet<>();
        tracks.add(track);
        Album album = Album.builder().albumId(1L).name("Alllllbum").tracks(tracks).build();
        albumRepo.save(album);
    }

}