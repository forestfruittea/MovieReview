package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.impl.MovieMapperImpl;
import com.example.movierev.Repositories.MovieRepository;
import com.example.movierev.Services.MovieService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
        @Stateless
 public class MovieServiceImpl implements MovieService {
            private final MovieRepository movieRepository;
            private final MovieMapperImpl movieMapper;
            @Inject
            public MovieServiceImpl(MovieRepository movieRepository, MovieMapperImpl movieMapper) {
                this.movieRepository = movieRepository;
                this.movieMapper = movieMapper;
            }

            @Override
    public MovieDto createMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieMapper.toEntity(movieDto);
        movieEntity = movieRepository.createMovie(movieEntity);
        return movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieMapper.toEntity(movieDto);
        movieEntity = movieRepository.updateMovie(movieEntity);
        return movieMapper.toDto(movieEntity);
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteMovie(movieId);
    }

    @Override
    public Optional<MovieDto> getMovieById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findMovieById(id);
        return movieEntity.map(movieMapper::toDto);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieEntity> movieEntities = movieRepository.findAllMovies();
        return movieEntities.stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }
}
