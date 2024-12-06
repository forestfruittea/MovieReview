package com.example.movierev.Repositories;

import com.example.movierev.Entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    GenreEntity save(GenreEntity genreEntity);

    GenreEntity update(GenreEntity genreEntity);

    void delete(Long genreId);

    Optional<GenreEntity> findById(Long genreId);
    Optional<GenreEntity> findByName(String name);

    List<GenreEntity> findAll();
}
