package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.DirectorDto;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Mappers.impl.DirectorMapper;
import com.example.movierev.Repositories.DirectorRepository;
import com.example.movierev.Services.DirectorService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Stateless
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
        return directorMapper.toDto(directorEntity);
    }

    @Override
    public DirectorDto update(DirectorDto directorDto) {
        DirectorEntity directorEntity = directorMapper.toEntity(directorDto);
        directorEntity = directorRepository.update(directorEntity);
        return directorMapper.toDto(directorEntity);
    }

    @Override
    public void delete(Long directorId) {
        directorRepository.delete(directorId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<DirectorDto> findById(Long directorId) {
        Optional<DirectorEntity> directorEntity = directorRepository.findById(directorId);
        return directorEntity.map(directorMapper::toDto);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<DirectorDto> findAll() {
        List<DirectorEntity> directorEntities = directorRepository.findAll();
        return directorEntities.stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto findOrSave(DirectorDto directorDto) {
        Optional<DirectorEntity> existing = directorRepository.findByName(directorDto.getName());
        if (existing.isPresent()) {
            return directorMapper.toDto(existing.get());
        }
        DirectorEntity newDirector = directorMapper.toEntity(directorDto);
        DirectorEntity savedDirector = directorRepository.save(newDirector);
        return directorMapper.toDto(savedDirector);
    }
}
