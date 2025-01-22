package com.example.movierev.service;

import com.example.movierev.dto.ActorDto;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    ActorDto save(ActorDto actorDto);
    void delete(Long actorId);
    Optional<ActorDto> findById(Long actorId);
    List<ActorDto> findAll();
    List<ActorDto> findAllSorted();
    ActorDto findOrSave(ActorDto actorDto);

    //TODO   DirectorDto update(DirectorDto directorDto);

}
