package com.example.movierev.Repositories;

import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository {
    DirectorEntity createDirector(DirectorEntity directorEntity);

    DirectorEntity updateDirector(DirectorEntity directorEntity);

    void deleteDirector(Long directorId);

    Optional<DirectorEntity> findDirectorById(Long directorId);
    Optional<DirectorEntity> findDirectorByName(String name);

    List<DirectorEntity> findAllDirectors();
}
