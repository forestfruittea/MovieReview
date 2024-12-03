package com.example.movierev.Repositories;

import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    GenreEntity createGenre(GenreEntity genreEntity);

    GenreEntity updateGenre(GenreEntity genreEntity);

    void deleteGenre(Long genreId);

    Optional<GenreEntity> findGenreById(Long genreId);
    Optional<GenreEntity> findGenreByName(String name);

    List<GenreEntity> findAllGenres();
}
