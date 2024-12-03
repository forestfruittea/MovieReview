package com.example.movierev.Services;

import com.example.movierev.DTOs.DirectorDto;
import com.example.movierev.DTOs.GenreDto;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    DirectorDto createDirector(DirectorDto directorDto);
    DirectorDto updateDirector(DirectorDto directorDto);
    void deleteDirector(Long directorId);
    Optional<DirectorDto> getDirectorById(Long directorId);
    List<DirectorDto> getAllDirectors();
    public DirectorDto findOrCreate(DirectorDto directorDto);
}
