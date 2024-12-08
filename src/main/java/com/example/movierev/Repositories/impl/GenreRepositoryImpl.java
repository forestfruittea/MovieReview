package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Repositories.GenreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public GenreEntity save(GenreEntity genreEntity) {
        entityManager.persist(genreEntity);
        return genreEntity;
    }

    @Override
    public GenreEntity update(GenreEntity genreEntity) {
        return entityManager.merge(genreEntity);
    }

    @Override
    public void delete(Long genreId) {
        GenreEntity genreEntity = entityManager.find(GenreEntity.class, genreId);
        if (genreEntity !=null) entityManager.remove(genreEntity);
    }
    @Override
    public Optional<GenreEntity> findById(Long genreId) {
        return Optional.ofNullable(entityManager.find(GenreEntity.class, genreId));
    }
    @Override
    public Optional<GenreEntity> findByName(String name) {
        try {
            GenreEntity genreEntity = entityManager
                    .createQuery("SELECT g FROM GenreEntity g WHERE g.name = :name", GenreEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(genreEntity);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    @Override
    public List<GenreEntity> findAllByMovieId(Long movieId) {
        return entityManager.createQuery(
                        "SELECT DISTINCT g FROM GenreEntity g " +
                                "JOIN g.movies m WHERE m.id = :movieId",
                        GenreEntity.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }
    @Override
    public List<GenreEntity> findAll() {
        return entityManager.createQuery("SELECT g FROM GenreEntity g", GenreEntity.class).getResultList();
    }
}
