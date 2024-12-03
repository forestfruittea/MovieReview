package com.example.movierev.Repositories;

import com.example.movierev.Entities.MovieEntity;
import java.util.List;
import java.util.Optional;


public interface MovieRepository {
    MovieEntity createMovie(MovieEntity movieEntity);

    MovieEntity updateMovie(MovieEntity movieEntity);

    void deleteMovie(Long movieId);

    Optional<MovieEntity> findMovieById(Long movieId);

    List<MovieEntity> findAllMovies();
}
