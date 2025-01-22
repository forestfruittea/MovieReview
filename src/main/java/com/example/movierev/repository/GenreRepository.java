package com.example.movierev.repository;

import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.entity.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    GenreEntity save(GenreEntity genreEntity);
    void delete(Long genreId);
    Optional<GenreEntity> findById(Long genreId);
    Optional<GenreEntity> findByName(String name);
    List<GenreEntity> findAllByMovieId(Long movieId);
    List<GenreEntity> findAllSortedByName();
    List<GenreEntity> findAll();

    //TODO    GenreEntity update(GenreEntity genreEntity);

}
