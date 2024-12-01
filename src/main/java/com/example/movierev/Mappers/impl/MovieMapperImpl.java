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
        dto.setCountry(movieEntity.getCountry());
        dto.setStarringRole(movieEntity.getStarringRole());
        dto.setDirector(movieEntity.getDirector());
        dto.setReleaseDate(movieEntity.getReleaseDate());
        dto.setGenre(movieEntity.getGenre());
        dto.setPosterPath(movieEntity.getPosterPath());
        dto.setLength(movieEntity.getLength());
        dto.setBudget(movieEntity.getBudget());
        dto.setBoxOffice(movieEntity.getBoxOffice());
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
        entity.setCountry(movieDto.getCountry());
        entity.setStarringRole(movieDto.getStarringRole());
        entity.setDirector(movieDto.getDirector());
        entity.setReleaseDate(movieDto.getReleaseDate());
        entity.setGenre(movieDto.getGenre());
        entity.setPosterPath(movieDto.getPosterPath());
        entity.setLength(movieDto.getLength());
        entity.setBudget(movieDto.getBudget());
        entity.setBoxOffice(movieDto.getBoxOffice());
        return entity;
    }
}

