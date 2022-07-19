package com.example.infosystem.controllers;

import com.example.infosystem.converter.TrackConverter;
import com.example.infosystem.dto.MessageResponse;
import com.example.infosystem.dto.TrackDto;
import com.example.infosystem.repos.TrackRepo;
import com.example.infosystem.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    TrackRepo trackRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public Set<TrackDto> getAllTracks(){
        return TrackConverter.toDtos(new HashSet<>(trackRepo.findAll()));
    }

    @PostMapping("/new")
    public ResponseEntity<MessageResponse> createNewTrack(@RequestBody TrackDto trackDto){
        if(trackRepo.existsByTrackId(trackDto.getTrackId())) {
            return ResponseEntity.
                    badRequest().
                    body(new MessageResponse("Трек с таким id уже существует"));
        }
        trackRepo.save(TrackConverter.toEntity(trackDto));
        return ResponseEntity.ok(new MessageResponse("Track CREATED"));
    }
}
