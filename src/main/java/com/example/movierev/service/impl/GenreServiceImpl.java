package com.example.movierev.service.impl;

import com.example.movierev.dto.DirectorDto;
import com.example.movierev.dto.GenreDto;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.entity.GenreEntity;
import com.example.movierev.mapper.impl.GenreMapper;
import com.example.movierev.repository.GenreRepository;
import com.example.movierev.service.GenreService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Slf4j
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
        log.debug("saves genre");
        return genreMapper.toDto(genreEntity);
    }
//TODO
//    @Override
//    public GenreDto update(GenreDto genreDto) {
//        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
//        genreEntity = genreRepository.update(genreEntity);
//        log.debug("updates genre");
//        return genreMapper.toDto(genreEntity);
//    }

    @Override
    public void delete(Long genreId) {
        log.debug("deletes genre");
        genreRepository.delete(genreId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<GenreDto> findById(Long genreId) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(genreId);
        log.debug("finds genre by id");
        return genreEntity.map(genreMapper::toDto);
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<GenreDto> findAllByMovieId(Long movieId){
        List<GenreEntity> genreEntities = genreRepository.findAllByMovieId(movieId);
        log.debug("finds all genres by movie id");
        return genreEntities.stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<GenreDto> findAll() {
        List<GenreEntity> genreEntities = genreRepository.findAll();
        log.debug("finds all genres");
        return genreEntities.stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<GenreDto> findAllSorted() {
        List<GenreEntity> genreEntities = genreRepository.findAllSortedByName();
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
        log.debug("saves genre if not exist");
        GenreEntity newGenre = genreMapper.toEntity(genreDto);
        GenreEntity savedGenre = genreRepository.save(newGenre);
        return genreMapper.toDto(savedGenre);
    }
}
