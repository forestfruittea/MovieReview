package com.example.movierev.Repositories;

import com.example.movierev.Entities.ActorEntity;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    ActorEntity save(ActorEntity actorEntity);

    ActorEntity update(ActorEntity actorEntity);

    void delete(Long actorId);

    Optional<ActorEntity> findById(Long actorId);
    Optional<ActorEntity> findByName(String name);

    List<ActorEntity> findAll();
}

