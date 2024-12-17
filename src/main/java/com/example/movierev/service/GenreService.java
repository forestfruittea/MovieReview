package com.example.movierev.service;

import com.example.movierev.dto.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    GenreDto save(GenreDto genreDto);
    GenreDto update(GenreDto genreDto);
    void delete(Long genreId);
    Optional<GenreDto> findById(Long genreId);
    public List<GenreDto> findAllByMovieId(Long movieId);
    List<GenreDto> findAll();
    public GenreDto findOrSave(GenreDto genreDto);
    public List<GenreDto> findAllSorted();
}
