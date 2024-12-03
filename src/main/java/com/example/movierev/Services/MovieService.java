package com.example.movierev.Services;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.MovieEntity;
import jakarta.ejb.EJB;

import java.util.List;
import java.util.Optional;
public interface MovieService {
    void createMovie(MovieDto movieDto);
    MovieDto updateMovie(MovieDto movieDto);
    void deleteMovie(Long movieId);
    Optional<MovieDto> getMovieById(Long id);
    List<MovieDto> getAllMovies();

}