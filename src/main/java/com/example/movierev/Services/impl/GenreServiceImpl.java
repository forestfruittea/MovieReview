package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.GenreDto;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Mappers.impl.GenreMapper;
import com.example.movierev.Repositories.GenreRepository;
import com.example.movierev.Services.GenreService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Inject
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }
    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
        genreEntity = genreRepository.createGenre(genreEntity);
        return genreMapper.toDto(genreEntity);
    }

    @Override
    public GenreDto updateGenre(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
        genreEntity = genreRepository.updateGenre(genreEntity);
        return genreMapper.toDto(genreEntity);
    }

    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteGenre(genreId);
    }

    @Override
    public Optional<GenreDto> getGenreById(Long genreId) {
        Optional<GenreEntity> genreEntity = genreRepository.findGenreById(genreId);
        return genreEntity.map(genreMapper::toDto);
    }

    @Override
    public List<GenreDto> getAllGenres() {
        List<GenreEntity> genreEntities = genreRepository.findAllGenres();
        return genreEntities.stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto findOrCreate(GenreDto genreDto) {
        Optional<GenreEntity> existing = genreRepository.findGenreByName(genreDto.getName());
        if (existing.isPresent()) {
            return genreMapper.toDto(existing.get());
        }
        GenreEntity newGenre = genreMapper.toEntity(genreDto);
        GenreEntity savedGenre = genreRepository.createGenre(newGenre);
        return genreMapper.toDto(savedGenre);
    }
}
