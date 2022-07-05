package com.example.infosystem.repos;

import com.example.infosystem.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends JpaRepository<Track, Long> {

}
