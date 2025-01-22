package com.example.movierev.service.impl;

import com.example.movierev.dto.ActorDto;
import com.example.movierev.entity.ActorEntity;
import com.example.movierev.mapper.impl.ActorMapper;
import com.example.movierev.repository.ActorRepository;
import com.example.movierev.service.ActorService;
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
        log.debug("saves actor");
        return actorMapper.toDto(actorEntity);
    }
//TODO
//    @Override
//    public ActorDto update(ActorDto actorDto) {
//        ActorEntity actorEntity = actorMapper.toEntity(actorDto);
//        actorEntity = actorRepository.update(actorEntity);
//        log.debug("updates an actor");
//        return actorMapper.toDto(actorEntity);
//    }

    @Override
    public void delete(Long id) {
        log.debug("deletes actor by id");
        actorRepository.delete(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<ActorDto> findById(Long id) {
        Optional<ActorEntity> actorEntity = actorRepository.findById(id);
        log.debug("finds actor sorted by id");
        return actorEntity.map(actorMapper::toDto);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ActorDto> findAll() {
        List<ActorEntity> actorEntities = actorRepository.findAll();
        log.debug("finds all actors");

        return actorEntities.stream()
                .map(actorMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ActorDto> findAllSorted() {
        List<ActorEntity> actorEntities = actorRepository.findAllSortedByName();
        return actorEntities.stream()
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
        log.debug("saves actor if not exist ");
        return actorMapper.toDto(savedActor);
    }
}
