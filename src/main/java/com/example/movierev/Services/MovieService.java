package com.example.movierev.Services;

import com.example.movierev.DTOs.MovieDto;

import java.util.List;
import java.util.Optional;
public interface MovieService {
    void save(MovieDto movieDto);
    MovieDto update(MovieDto movieDto);
    void delete(Long movieId);
    Optional<MovieDto> findById(Long id);
    List<MovieDto> findAll();

}