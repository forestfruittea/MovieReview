package com.example.movierev.service;

import com.example.movierev.dto.DirectorDto;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    DirectorDto save(DirectorDto directorDto);
    void delete(Long directorId);
    Optional<DirectorDto> findById(Long directorId);
    List<DirectorDto> findAll();
    List<DirectorDto> findAllSorted();
    DirectorDto findOrSave(DirectorDto directorDto);

    //TODO   DirectorDto update(DirectorDto directorDto);

}
