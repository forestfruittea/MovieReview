package com.example.movierev.Repositories;

import com.example.movierev.Entities.DirectorEntity;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository {
    DirectorEntity save(DirectorEntity directorEntity);

    DirectorEntity update(DirectorEntity directorEntity);

    void delete(Long directorId);

    Optional<DirectorEntity> findById(Long directorId);
    Optional<DirectorEntity> findByName(String name);

    List<DirectorEntity> findAll();
}
