package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.Mapper;
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