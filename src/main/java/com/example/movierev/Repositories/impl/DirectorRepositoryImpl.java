package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Repositories.DirectorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class DirectorRepositoryImpl implements DirectorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public DirectorEntity createDirector(DirectorEntity directorEntity) {
        entityManager.persist(directorEntity);
        return directorEntity;
    }

    @Override
    public DirectorEntity updateDirector(DirectorEntity directorEntity) {
        return entityManager.merge(directorEntity);
    }

    @Override
    public void deleteDirector(Long directorId) {
        DirectorEntity directorEntity = entityManager.find(DirectorEntity.class, directorId);
        if (directorEntity !=null) entityManager.remove(directorEntity);
    }

    @Override
    public Optional<DirectorEntity> findDirectorById(Long directorId) {
        return Optional.ofNullable(entityManager.find(DirectorEntity.class, directorId));
    }
    @Override
    public Optional<DirectorEntity> findDirectorByName(String name) {
        try {
            DirectorEntity director = entityManager
                    .createQuery("SELECT d FROM DirectorEntity d WHERE d.name = :name", DirectorEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(director);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<DirectorEntity> findAllDirectors() {
        return entityManager.createQuery("SELECT d FROM DirectorEntity d", DirectorEntity.class).getResultList();
    }
}
