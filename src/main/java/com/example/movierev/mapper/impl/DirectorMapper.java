package com.example.movierev.mapper.impl;

import com.example.movierev.dto.DirectorDto;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper implements Mapper<DirectorEntity, DirectorDto> {
    private final ModelMapper modelMapper;
    @Autowired
    public DirectorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DirectorEntity toEntity(DirectorDto directorDto) {
        return modelMapper.map(directorDto, DirectorEntity.class);
    }

    @Override
    public DirectorDto toDto(DirectorEntity directorEntity) {
        return modelMapper.map(directorEntity, DirectorDto.class);
    }
}
