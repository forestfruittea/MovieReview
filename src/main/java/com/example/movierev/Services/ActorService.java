package com.example.movierev.Services;

import com.example.movierev.DTOs.ActorDto;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    ActorDto save(ActorDto actorDto);
    ActorDto update(ActorDto actorDto);
    void delete(Long actorId);
    Optional<ActorDto> findById(Long actorId);
    List<ActorDto> findAll();
    public ActorDto findOrSave(ActorDto actorDto);
}
