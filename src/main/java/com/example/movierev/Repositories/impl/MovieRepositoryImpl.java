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
    public MovieEntity createMovie(MovieEntity movieEntity) {
        entityManager.persist(movieEntity);
        return movieEntity;
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movieEntity) {
        return entityManager.merge(movieEntity);
    }

    @Override
    public void deleteMovie(Long movieId) {
        MovieEntity movie = entityManager.find(MovieEntity.class, movieId);
        if (movie !=null) entityManager.remove(movie);
    }

    @Override
    public Optional<MovieEntity> findMovieById(Long movieId) {
        return Optional.ofNullable(entityManager.find(MovieEntity.class, movieId));
    }

    @Override
    public List<MovieEntity> findAllMovies() {
        return entityManager.createQuery("SELECT m FROM MovieEntity m", MovieEntity.class).getResultList();
    }
}
