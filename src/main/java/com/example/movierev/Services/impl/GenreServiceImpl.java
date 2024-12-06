package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.GenreDto;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Mappers.impl.GenreMapper;
import com.example.movierev.Repositories.GenreRepository;
import com.example.movierev.Services.GenreService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
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
    public GenreDto save(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
        genreEntity = genreRepository.save(genreEntity);
        return genreMapper.toDto(genreEntity);
    }

    @Override
    public GenreDto update(GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
        genreEntity = genreRepository.update(genreEntity);
        return genreMapper.toDto(genreEntity);
    }

    @Override
    public void delete(Long genreId) {
        genreRepository.delete(genreId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<GenreDto> findById(Long genreId) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(genreId);
        return genreEntity.map(genreMapper::toDto);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<GenreDto> findAll() {
        List<GenreEntity> genreEntities = genreRepository.findAll();
        return genreEntities.stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto findOrSave(GenreDto genreDto) {
        Optional<GenreEntity> existing = genreRepository.findByName(genreDto.getName());
        if (existing.isPresent()) {
            return genreMapper.toDto(existing.get());
        }
        GenreEntity newGenre = genreMapper.toEntity(genreDto);
        GenreEntity savedGenre = genreRepository.save(newGenre);
        return genreMapper.toDto(savedGenre);
    }
}
