package com.example.movierev.service;

import com.example.movierev.dto.ActorDto;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    ActorDto save(ActorDto actorDto);
    ActorDto update(ActorDto actorDto);
    void delete(Long actorId);
    Optional<ActorDto> findById(Long actorId);
    List<ActorDto> findAll();
    public List<ActorDto> findAllSorted();
    public ActorDto findOrSave(ActorDto actorDto);
}
