package com.example.movierev.mapper.impl;

import com.example.movierev.dto.DirectorDto;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.mapper.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class DirectorMapper implements Mapper<DirectorEntity, DirectorDto> {
    @Inject
    private  ModelMapper modelMapper;

    @Override
    public DirectorEntity toEntity(DirectorDto directorDto) {
        return modelMapper.map(directorDto, DirectorEntity.class);
    }

    @Override
    public DirectorDto toDto(DirectorEntity directorEntity) {
        return modelMapper.map(directorEntity, DirectorDto.class);
    }
}
