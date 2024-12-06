package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.DirectorDto;
import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.Mapper;
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
