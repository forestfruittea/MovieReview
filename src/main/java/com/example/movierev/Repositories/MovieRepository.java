package com.example.movierev.Repositories;

import com.example.movierev.Entities.MovieEntity;
import java.util.List;
import java.util.Optional;


public interface MovieRepository {
    MovieEntity save(MovieEntity movieEntity);

    MovieEntity update(MovieEntity movieEntity);

    void delete(Long movieId);

    Optional<MovieEntity> findById(Long movieId);

    List<MovieEntity> findAllByGenreId(Long genreId);
    List<MovieEntity> findAll();
}
