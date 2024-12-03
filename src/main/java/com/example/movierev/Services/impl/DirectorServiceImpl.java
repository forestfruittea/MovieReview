package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.DirectorDto;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Mappers.impl.DirectorMapper;
import com.example.movierev.Repositories.DirectorRepository;
import com.example.movierev.Services.DirectorService;
import jakarta.ejb.Stateless;
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
    public DirectorDto createDirector(DirectorDto directorDto) {
        DirectorEntity directorEntity = directorMapper.toEntity(directorDto);
        directorEntity = directorRepository.createDirector(directorEntity);
        return directorMapper.toDto(directorEntity);
    }

    @Override
    public DirectorDto updateDirector(DirectorDto directorDto) {
        DirectorEntity directorEntity = directorMapper.toEntity(directorDto);
        directorEntity = directorRepository.updateDirector(directorEntity);
        return directorMapper.toDto(directorEntity);
    }

    @Override
    public void deleteDirector(Long directorId) {
        directorRepository.deleteDirector(directorId);
    }

    @Override
    public Optional<DirectorDto> getDirectorById(Long directorId) {
        Optional<DirectorEntity> directorEntity = directorRepository.findDirectorById(directorId);
        return directorEntity.map(directorMapper::toDto);
    }

    @Override
    public List<DirectorDto> getAllDirectors() {
        List<DirectorEntity> directorEntities = directorRepository.findAllDirectors();
        return directorEntities.stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto findOrCreate(DirectorDto directorDto) {
        Optional<DirectorEntity> existing = directorRepository.findDirectorByName(directorDto.getName());
        if (existing.isPresent()) {
            return directorMapper.toDto(existing.get());
        }
        DirectorEntity newDirector = directorMapper.toEntity(directorDto);
        DirectorEntity savedDirector = directorRepository.createDirector(newDirector);
        return directorMapper.toDto(savedDirector);
    }
}
