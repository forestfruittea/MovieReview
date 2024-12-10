package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Repositories.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class MovieRepositoryImpl implements MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        entityManager.persist(movieEntity);
        return movieEntity;
    }

    @Override
    public MovieEntity update(MovieEntity movieEntity) {
        return entityManager.merge(movieEntity);
    }

    @Override
    public void delete(Long movieId) {
        MovieEntity movie = entityManager.find(MovieEntity.class, movieId);
        if (movie !=null) entityManager.remove(movie);
    }

    @Override
    public Optional<MovieEntity> findById(Long movieId) {
        MovieEntity movie = entityManager.createQuery(
                        "SELECT m FROM MovieEntity m " +
                                "LEFT JOIN FETCH m.actors " +
                                "LEFT JOIN FETCH m.genres " +
                                "LEFT JOIN FETCH m.director " +
                                "WHERE m.id = :id", MovieEntity.class)
                .setParameter("id", movieId)
                .getSingleResult();
        return Optional.ofNullable(movie);
    }

    @Override
    public List<MovieEntity> findAllByGenreId(Long genreId) {
        return entityManager.createQuery(
                        "SELECT DISTINCT m FROM MovieEntity m " +
                                "LEFT JOIN FETCH m.actors " +
                                "LEFT JOIN FETCH m.director " +
                                "JOIN m.genres g WHERE g.id = :genreId",
                        MovieEntity.class)
                .setParameter("genreId", genreId)
                .getResultList();
    }
    @Override
    public List<MovieEntity> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT DISTINCT m FROM MovieEntity m " +
                                "WHERE m.title LIKE :name",
                        MovieEntity.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
    @Override
    public List<MovieEntity> findAll() {
        return entityManager.createQuery(
                        "SELECT DISTINCT m FROM MovieEntity m " +
                                "LEFT JOIN FETCH m.actors " +
                                "LEFT JOIN FETCH m.genres " +
                                "LEFT JOIN FETCH m.director", MovieEntity.class)
                .getResultList();
    }
}
