package com.example.infosystem.entity;



import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Playlist{
    @Id
    @SequenceGenerator(name = "playlist_id", sequenceName = "playlist_id", allocationSize = 1)
    @GeneratedValue(generator = "playlist_id", strategy = GenerationType.SEQUENCE)
    private Long playlistId;
    @Column(nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Set<Track> tracks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Playlist playlist = (Playlist) o;
        return playlistId != null && Objects.equals(playlistId, playlist.playlistId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}