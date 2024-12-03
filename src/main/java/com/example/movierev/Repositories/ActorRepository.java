package com.example.movierev.Repositories;

import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    ActorEntity createActor(ActorEntity actorEntity);

    ActorEntity updateActor(ActorEntity actorEntity);

    void deleteActor(Long actorId);

    Optional<ActorEntity> findActorById(Long actorId);
    Optional<ActorEntity> findActorByName(String name);

    List<ActorEntity> findAllActors();
}

