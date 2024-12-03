package com.example.movierev.Services;

import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.DTOs.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    GenreDto createGenre(GenreDto genreDto);
    GenreDto updateGenre(GenreDto genreDto);
    void deleteGenre(Long genreId);
    Optional<GenreDto> getGenreById(Long genreId);
    List<GenreDto> getAllGenres();
    public GenreDto findOrCreate(GenreDto genreDto);
}
