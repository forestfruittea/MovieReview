package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.Mapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieMapperImpl implements Mapper<MovieEntity, MovieDto> {
    @Override
    public MovieDto toDto(MovieEntity movieEntity) {
        if (movieEntity == null) {
            return null;
        }
        MovieDto dto = new MovieDto();
        dto.setId(movieEntity.getId());
        dto.setTitle(movieEntity.getTitle());
        dto.setDescription(movieEntity.getDescription());
        dto.setReleaseDate(movieEntity.getReleaseDate());
        dto.setGenre(movieEntity.getGenre());
        return dto;
    }

    @Override
    public MovieEntity toEntity(MovieDto movieDto) {
        if (movieDto == null) {
            return null;
        }
        MovieEntity entity = new MovieEntity();
        entity.setId(movieDto.getId());
        entity.setTitle(movieDto.getTitle());
        entity.setDescription(movieDto.getDescription());
        entity.setReleaseDate(movieDto.getReleaseDate());
        entity.setGenre(movieDto.getGenre());
        return entity;
    }
}

