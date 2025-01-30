package com.example.movierev.service;

import com.example.movierev.dto.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    GenreDto save(GenreDto genreDto);
    void delete(Long genreId);
    Optional<GenreDto> findById(Long genreId);
    List<GenreDto> findAllByMovieId(Long movieId);
    List<GenreDto> findAll();
    GenreDto findOrSave(GenreDto genreDto);
    List<GenreDto> findAllSorted();

    List<GenreDto> getGenresByNames(List<String> genreNames);

    //TODO GenreDto update(GenreDto genreDto);

}
