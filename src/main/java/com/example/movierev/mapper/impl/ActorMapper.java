package com.example.movierev.mapper.impl;

import com.example.movierev.dto.ActorDto;
import com.example.movierev.entity.ActorEntity;
import com.example.movierev.mapper.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ActorMapper implements Mapper<ActorEntity, ActorDto> {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public ActorEntity toEntity(ActorDto actorDto) {
        return modelMapper.map(actorDto, ActorEntity.class);
    }

    @Override
    public ActorDto toDto(ActorEntity actorEntity) {
        return modelMapper.map(actorEntity, ActorDto.class);
    }
}