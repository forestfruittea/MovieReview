package com.example.movierev.repository;

import com.example.movierev.entity.MovieEntity;
import java.util.List;
import java.util.Optional;


public interface MovieRepository {
    MovieEntity save(MovieEntity movieEntity);
    void delete(Long movieId);
    List<MovieEntity> findByName(String name);
    Optional<MovieEntity> findById(Long movieId);
    List<MovieEntity> findMoviesByPage(int page, int size);
    long count();
    List<MovieEntity> findAllByGenreId(Long genreId);
    List<MovieEntity> findAll();

    //TODO    MovieEntity update(MovieEntity movieEntity);

}
