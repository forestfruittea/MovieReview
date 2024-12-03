package com.example.movierev.Services;

import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Repositories.ActorRepository;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    ActorDto createActor(ActorDto actorDto);
    ActorDto updateActor(ActorDto actorDto);
    void deleteActor(Long actorId);
    Optional<ActorDto> getActorById(Long actorId);
    List<ActorDto> getAllActors();
    public ActorDto findOrCreate(ActorDto actorDto);
}
