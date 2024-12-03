package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Mappers.impl.ActorMapper;
import com.example.movierev.Repositories.ActorRepository;
import com.example.movierev.Services.ActorService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Stateless
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Inject
    public ActorServiceImpl(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }
    @Override
    public ActorDto createActor(ActorDto actorDto) {
        ActorEntity actorEntity = actorMapper.toEntity(actorDto);
        actorEntity = actorRepository.createActor(actorEntity);
        return actorMapper.toDto(actorEntity);
    }

    @Override
    public ActorDto updateActor(ActorDto actorDto) {
        ActorEntity actorEntity = actorMapper.toEntity(actorDto);
        actorEntity = actorRepository.updateActor(actorEntity);
        return actorMapper.toDto(actorEntity);
    }

    @Override
    public void deleteActor(Long actorId) {
        actorRepository.deleteActor(actorId);
    }

    @Override
    public Optional<ActorDto> getActorById(Long actorId) {
        Optional<ActorEntity> actorEntity = actorRepository.findActorById(actorId);
        return actorEntity.map(actorMapper::toDto);
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<ActorEntity> actorEntities = actorRepository.findAllActors();
        return actorEntities.stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActorDto findOrCreate(ActorDto actorDto) {
        Optional<ActorEntity> existing = actorRepository.findActorByName(actorDto.getName());
        if (existing.isPresent()) {
            return actorMapper.toDto(existing.get());
        }
        ActorEntity newActor = actorMapper.toEntity(actorDto);
        ActorEntity savedActor = actorRepository.createActor(newActor);
        return actorMapper.toDto(savedActor);
    }
}
