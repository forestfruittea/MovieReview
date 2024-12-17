package com.example.movierev.repository;

import com.example.movierev.entity.ActorEntity;

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

