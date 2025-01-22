package com.example.movierev.service.impl;

import com.example.movierev.dto.GenreDto;
import com.example.movierev.dto.MovieDto;
import com.example.movierev.entity.ActorEntity;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.entity.GenreEntity;
import com.example.movierev.entity.MovieEntity;
import com.example.movierev.mapper.impl.ActorMapper;
import com.example.movierev.mapper.impl.DirectorMapper;
import com.example.movierev.mapper.impl.GenreMapper;
import com.example.movierev.mapper.impl.MovieMapper;
import com.example.movierev.repository.ActorRepository;
import com.example.movierev.repository.DirectorRepository;
import com.example.movierev.repository.GenreRepository;
import com.example.movierev.repository.MovieRepository;
import com.example.movierev.service.GenreService;
import com.example.movierev.service.MovieService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
        @Stateless
        @Slf4j
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
                log.debug("saves movie");
                movieRepository.save(movieEntity);
            }

//TODO
//            @Override
//            public MovieDto update(MovieDto movieDto) {
//                MovieEntity movieEntity = movieMapper.toEntity(movieDto);
//                movieEntity = movieRepository.update(movieEntity);
//                log.debug("updates movie");
//                return movieMapper.toDto(movieEntity);
//            }

            @Override
            public void delete(Long movieId) {
                log.debug("deletes movie");
                movieRepository.delete(movieId);
            }

            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public Optional<MovieDto> findById(Long id) {
                Optional<MovieEntity> movieEntity = movieRepository.findById(id);
                log.debug("finds movie by id");
                return movieEntity.map(movieMapper::toDto);
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public List<MovieDto> findByName(String name){
                List<MovieEntity> movieEntities = movieRepository.findByName(name);
                log.debug("finds movie by name");
                return movieEntities.stream()
                        .map(movieMapper::toDto)
                        .collect(Collectors.toList());
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public List<MovieDto> findAllByGenreId(Long genreId){
                List<MovieEntity> movieEntities = movieRepository.findAllByGenreId(genreId);
                log.debug("finds all movies by genre id");
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
                log.debug("finds all movies");

                return movieEntities.stream()
                        .map(movieMapper::toDto)
                        .collect(Collectors.toList());
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public List<MovieDto> findMoviesByPage(int page, int size) {
                List<MovieEntity> movieEntities = movieRepository.findMoviesByPage(page, size);
                log.debug("finds movies for page {} with size {}", page, size);
                return movieEntities.stream()
                        .map(movieMapper::toDto)
                        .collect(Collectors.toList());
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public long count() {
                return movieRepository.count();
            }
            @Override
            @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
            public List<MovieDto> findAllSorted() {
                List<MovieEntity> movies = movieRepository.findAll();
                log.debug("finds all movies sorted by name");

                return movies.stream()
                        .sorted((m1, m2) -> {
                            String title1 = m1.getTitle().toLowerCase();
                            String title2 = m2.getTitle().toLowerCase();
                            return title1.compareTo(title2);
                        })
                        .map(movieMapper::toDto)
                        .collect(Collectors.toList());

            }
        }
