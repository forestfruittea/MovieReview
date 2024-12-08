package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.GenreDto;
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
import com.example.movierev.Services.GenreService;
import com.example.movierev.Services.MovieService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
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
            private final GenreService genreService;


            @Inject
            public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, ActorRepository actorRepository, DirectorRepository directorRepository, MovieMapper movieMapper, DirectorMapper directorMapper, ActorMapper actorMapper, GenreMapper genreMapper, GenreService genreService) {
                this.movieRepository = movieRepository;
                this.genreRepository = genreRepository;
                this.actorRepository = actorRepository;
                this.directorRepository = directorRepository;
                this.movieMapper = movieMapper;
                this.directorMapper = directorMapper;
                this.actorMapper = actorMapper;
                this.genreMapper = genreMapper;
                this.genreService = genreService;
            }

            public void save(MovieDto movieDto) {
                DirectorEntity directorEntity = directorRepository.findByName(movieDto.getDirector().getName())
                        .orElseGet(() -> directorRepository.save(directorMapper.toEntity(movieDto.getDirector())));

                List<ActorEntity> actorEntities = movieDto.getActors().stream()
                        .map(actorDto -> actorRepository.findByName(actorDto.getName())
                                .orElseGet(() -> actorRepository.save(actorMapper.toEntity(actorDto))))
                        .collect(Collectors.toList());

                List<GenreEntity> genreEntities = movieDto.getGenres().stream()
                        .map(genreDto -> genreRepository.findByName(genreDto.getName())
                                .orElseGet(() -> genreRepository.save(genreMapper.toEntity(genreDto))))
                        .collect(Collectors.toList());

                MovieEntity movieEntity = movieMapper.toEntity(movieDto);
                movieEntity.setDirector(directorEntity);
                movieEntity.setActors(actorEntities);
                movieEntity.setGenres(genreEntities);

                movieRepository.save(movieEntity);
            }


            @Override
            public MovieDto update(MovieDto movieDto) {
                MovieEntity movieEntity = movieMapper.toEntity(movieDto);
                movieEntity = movieRepository.update(movieEntity);
                return movieMapper.toDto(movieEntity);
            }

            @Override
            public void delete(Long movieId) {
                movieRepository.delete(movieId);
            }

            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public Optional<MovieDto> findById(Long id) {
                Optional<MovieEntity> movieEntity = movieRepository.findById(id);
                return movieEntity.map(movieMapper::toDto);
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public List<MovieDto> findAllByGenreId(Long genreId){
                List<MovieEntity> movieEntities = movieRepository.findAllByGenreId(genreId);

                // For each movie, fetch genres using the findAllByMovieId method
                return movieEntities.stream()
                        .map(movie -> {
                            List<GenreDto> genreDtos = genreService.findAllByMovieId(movie.getId());  // Assuming movie.getId() gives the movie's ID
                            MovieDto movieDto = movieMapper.toDto(movie);
                            movieDto.setGenres(genreDtos);
                            return movieDto;
                        })
                        .collect(Collectors.toList());
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public List<MovieDto> findAll() {
                List<MovieEntity> movieEntities = movieRepository.findAll();

                return movieEntities.stream()
                        .map(movieMapper::toDto)
                        .collect(Collectors.toList());
            }
        }
