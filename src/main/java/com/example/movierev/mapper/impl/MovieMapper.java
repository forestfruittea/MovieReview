package com.example.movierev.mapper.impl;

import com.example.movierev.dto.MovieDto;
import com.example.movierev.mapper.Mapper;
import com.example.movierev.entity.MovieEntity;
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
