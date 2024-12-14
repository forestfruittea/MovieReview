package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Mappers.Mapper;
import com.example.movierev.Entities.MovieEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class MovieMapper implements Mapper<MovieEntity, MovieDto> {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public MovieEntity toEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, MovieEntity.class);
    }

    @Override
    public MovieDto toDto(MovieEntity movieEntity) {
        return modelMapper.map(movieEntity, MovieDto.class);
    }
}
