package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Repositories.ActorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class ActorRepositoryImpl implements ActorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ActorEntity createActor(ActorEntity actorEntity) {
        entityManager.persist(actorEntity);
        return actorEntity;
    }

    @Override
    public ActorEntity updateActor(ActorEntity actorEntity) {
        return entityManager.merge(actorEntity);
    }

    @Override
    public void deleteActor(Long actorId) {
        ActorEntity actorEntity = entityManager.find(ActorEntity.class, actorId);
        if (actorEntity !=null) entityManager.remove(actorEntity);
    }

    @Override
    public Optional<ActorEntity> findActorById(Long actorId) {
        return Optional.ofNullable(entityManager.find(ActorEntity.class, actorId));
    }
    @Override
    public Optional<ActorEntity> findActorByName(String name) {
        try {
            ActorEntity actorEntity = entityManager
                    .createQuery("SELECT a FROM ActorEntity a WHERE a.name = :name", ActorEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(actorEntity);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ActorEntity> findAllActors() {
        return entityManager.createQuery("SELECT a FROM ActorEntity a", ActorEntity.class).getResultList();
    }
}
