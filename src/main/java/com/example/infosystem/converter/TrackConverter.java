package com.example.infosystem.converter;

import com.example.infosystem.dto.TrackDto;
import com.example.infosystem.entity.Track;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrackConverter {
    public static TrackDto toDto(Track track) {
        return TrackDto.builder().
                trackId(track.getTrackId()).
                name(track.getName()).
                artist(track.getArtist()).
                time(track.getTime()).
                styles(track.getStyles()).
                albums(track.getAlbums()).
                build();
    }

    public static Track toEntity(TrackDto trackDto) {
        return Track.builder().
                trackId(trackDto.getTrackId()).
                name(trackDto.getName()).
                artist(trackDto.getArtist()).
                time(trackDto.getTime()).
                styles(trackDto.getStyles()).
                albums(trackDto.getAlbums()).
                build();
    }

    public static Set<TrackDto> toDtos(Set<Track> tracks){
        Set<TrackDto> trackDtos = new HashSet<>();
        for (Track p : tracks
        ) {
            trackDtos.add(toDto(p));
        }
        return trackDtos;
    }

    public static Set<Track> toEntityes(Set<TrackDto> trackDtos){
        Set<Track> tracks = new HashSet<>();
        for (TrackDto p : trackDtos
        ) {
            tracks.add(toEntity(p));
        }
        return tracks;
    }

    public static Set<Track> toEntityes(List<TrackDto> trackDtos){
        Set<Track> tracks = new HashSet<>();
        for (TrackDto p : trackDtos
        ) {
            tracks.add(toEntity(p));
        }
        return tracks;
    }
}
