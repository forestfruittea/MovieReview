package com.example.movierev.repository;

import com.example.movierev.entity.MovieEntity;
import java.util.List;
import java.util.Optional;


public interface MovieRepository {
    MovieEntity save(MovieEntity movieEntity);

    MovieEntity update(MovieEntity movieEntity);

    void delete(Long movieId);
    public List<MovieEntity> findByName(String name);

    Optional<MovieEntity> findById(Long movieId);

    List<MovieEntity> findAllByGenreId(Long genreId);
    List<MovieEntity> findAll();
}
