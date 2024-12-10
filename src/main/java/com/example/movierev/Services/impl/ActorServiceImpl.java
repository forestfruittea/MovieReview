package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.impl.ActorMapper;
import com.example.movierev.Repositories.ActorRepository;
import com.example.movierev.Services.ActorService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
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
    public ActorDto save(ActorDto actorDto) {
        ActorEntity actorEntity = actorMapper.toEntity(actorDto);
        actorEntity = actorRepository.save(actorEntity);
        return actorMapper.toDto(actorEntity);
    }

    @Override
    public ActorDto update(ActorDto actorDto) {
        ActorEntity actorEntity = actorMapper.toEntity(actorDto);
        actorEntity = actorRepository.update(actorEntity);
        return actorMapper.toDto(actorEntity);
    }

    @Override
    public void delete(Long actorId) {
        actorRepository.delete(actorId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<ActorDto> findById(Long actorId) {
        Optional<ActorEntity> actorEntity = actorRepository.findById(actorId);
        return actorEntity.map(actorMapper::toDto);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ActorDto> findAll() {
        List<ActorEntity> actorEntities = actorRepository.findAll();

        return actorEntities.stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ActorDto> findAllSorted() {
        List<ActorEntity> actorEntities = actorRepository.findAll();
        return actorEntities.stream()
                .sorted((m1, m2) -> {
                    String title1 = m1.getName().toLowerCase();
                    String title2 = m2.getName().toLowerCase();
                    return title1.compareTo(title2);
                })
                .map(actorMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public ActorDto findOrSave(ActorDto actorDto) {
        Optional<ActorEntity> existing = actorRepository.findByName(actorDto.getName());
        if (existing.isPresent()) {
            return actorMapper.toDto(existing.get());
        }
        ActorEntity newActor = actorMapper.toEntity(actorDto);
        ActorEntity savedActor = actorRepository.save(newActor);
        return actorMapper.toDto(savedActor);
    }
}
