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
public class Track {
    @Id
    @SequenceGenerator(name = "track_id", sequenceName = "track_id", allocationSize = 1)
    @GeneratedValue(generator = "track_id", strategy = GenerationType.SEQUENCE)
    private Long trackId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String artist;
    @Column(nullable = false)
    private Long time;
    @ManyToMany
    @JoinTable(
            name = "track_style",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "style_id", nullable = true))
    private Set<Style> styles;
    @ManyToOne
    @JoinColumn(name="album_id", nullable=true)
    private Album albums;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Track track = (Track) o;
        return trackId != null && Objects.equals(trackId, track.trackId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}