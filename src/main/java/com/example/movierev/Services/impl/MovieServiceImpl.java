package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.impl.ActorMapper;
import com.example.movierev.Mappers.impl.DirectorMapper;
import com.example.movierev.Mappers.impl.GenreMapper;
import com.example.movierev.Mappers.impl.MovieMapper;
import com.example.movierev.Repositories.ActorRepository;
import com.example.movierev.Repositories.DirectorRepository;
import com.example.movierev.Repositories.GenreRepository;
import com.example.movierev.Repositories.MovieRepository;
import com.example.movierev.Services.MovieService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
        @Stateless
 public class MovieServiceImpl implements MovieService {
            private final MovieRepository movieRepository;
            private final GenreRepository genreRepository;
            private final ActorRepository actorRepository;
            private final DirectorRepository directorRepository;
            private final MovieMapper movieMapper;
            private final DirectorMapper directorMapper;
            private final ActorMapper actorMapper;
            private final GenreMapper genreMapper;

            @Inject
            public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, ActorRepository actorRepository, DirectorRepository directorRepository, MovieMapper movieMapper, DirectorMapper directorMapper, ActorMapper actorMapper, GenreMapper genreMapper) {
                this.movieRepository = movieRepository;
                this.genreRepository = genreRepository;
                this.actorRepository = actorRepository;
                this.directorRepository = directorRepository;
                this.movieMapper = movieMapper;
                this.directorMapper = directorMapper;
                this.actorMapper = actorMapper;
                this.genreMapper = genreMapper;
            }

            public void createMovie(MovieDto movieDto) {
                DirectorEntity directorEntity = directorRepository.findDirectorByName(movieDto.getDirector().getName())
                        .orElseGet(() -> directorRepository.createDirector(directorMapper.toEntity(movieDto.getDirector())));

                List<ActorEntity> actorEntities = movieDto.getActors().stream()
                        .map(actorDto -> actorRepository.findActorByName(actorDto.getName())
                                .orElseGet(() -> actorRepository.createActor(actorMapper.toEntity(actorDto))))
                        .collect(Collectors.toList());

                List<GenreEntity> genreEntities = movieDto.getGenres().stream()
                        .map(genreDto -> genreRepository.findGenreByName(genreDto.getName())
                                .orElseGet(() -> genreRepository.createGenre(genreMapper.toEntity(genreDto))))
                        .collect(Collectors.toList());

                MovieEntity movieEntity = movieMapper.toEntity(movieDto);
                movieEntity.setDirector(directorEntity);
                movieEntity.setActors(actorEntities);
                movieEntity.setGenres(genreEntities);

                movieRepository.createMovie(movieEntity);
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
                        .map(movieMapper::toDto) // Use the MapStruct mapper to convert each entity
                        .collect(Collectors.toList());
            }
        }
