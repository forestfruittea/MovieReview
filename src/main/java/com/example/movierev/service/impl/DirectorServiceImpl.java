package com.example.movierev.service.impl;

import com.example.movierev.dto.ActorDto;
import com.example.movierev.dto.DirectorDto;
import com.example.movierev.dto.GenreDto;
import com.example.movierev.entity.ActorEntity;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.mapper.impl.DirectorMapper;
import com.example.movierev.repository.DirectorRepository;
import com.example.movierev.service.DirectorService;
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
public class DirectorServiceImpl implements DirectorService{
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Inject
    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }
    @Override
    public DirectorDto save(DirectorDto directorDto) {
        DirectorEntity directorEntity = directorMapper.toEntity(directorDto);
        directorEntity = directorRepository.save(directorEntity);
        log.debug("saves director");
        return directorMapper.toDto(directorEntity);
    }
//TODO
//    @Override
//    public DirectorDto update(DirectorDto directorDto) {
//        DirectorEntity directorEntity = directorMapper.toEntity(directorDto);
//        directorEntity = directorRepository.update(directorEntity);
//        log.debug("updates director");
//        return directorMapper.toDto(directorEntity);
//    }

    @Override
    public void delete(Long directorId) {
        directorRepository.delete(directorId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<DirectorDto> findById(Long directorId) {
        Optional<DirectorEntity> directorEntity = directorRepository.findById(directorId);
        log.debug("finds director by id");
        return directorEntity.map(directorMapper::toDto);
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<DirectorDto> findAllSorted() {
        List<DirectorEntity> directorEntities = directorRepository.findAllSortedByName();
        return directorEntities.stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<DirectorDto> findAll() {
        List<DirectorEntity> directorEntities = directorRepository.findAll();
        log.debug("finds all directors");
        return directorEntities.stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto findOrSave(DirectorDto directorDto) {
        Optional<DirectorEntity> existing = directorRepository.findByName(directorDto.getName());
        if (existing.isPresent()) {
            log.debug("saves director");
            return directorMapper.toDto(existing.get());
        }
        DirectorEntity newDirector = directorMapper.toEntity(directorDto);
        DirectorEntity savedDirector = directorRepository.save(newDirector);
        return directorMapper.toDto(savedDirector);
    }
}
