package com.example.infosystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Album {
    @Id
    @SequenceGenerator(name = "album_id", sequenceName = "album_id", allocationSize = 1)
    @GeneratedValue(generator = "album_id", strategy = GenerationType.SEQUENCE)
    private Long albumId;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "trackId", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private Set<Track> tracks;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Style> styles;

}
