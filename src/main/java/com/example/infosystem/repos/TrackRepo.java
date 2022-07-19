package com.example.infosystem.repos;

import com.example.infosystem.entity.Track;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepo extends JpaRepository<Track, Long> {
    @Override
    <S extends Track> List<S> findAll(Example<S> example);

    boolean existsByTrackId(Long aLong);


}
