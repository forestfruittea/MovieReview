package com.example.movierev.repository;

import com.example.movierev.entity.ActorEntity;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    ActorEntity save(ActorEntity actorEntity);
    void delete(Long id);
    Optional<ActorEntity> findById(Long id);
    Optional<ActorEntity> findByName(String name);
    List<ActorEntity> findAll();
    List<ActorEntity> findAllSortedByName();

    //TODO    ActorEntity update(ActorEntity actorEntity);

}

