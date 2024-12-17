package com.example.movierev.mapper.impl;

import com.example.movierev.dto.GenreDto;
import com.example.movierev.entity.GenreEntity;
import com.example.movierev.mapper.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class GenreMapper implements Mapper<GenreEntity, GenreDto> {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public GenreEntity toEntity(GenreDto genreDto) {
        return modelMapper.map(genreDto, GenreEntity.class);
    }

    @Override
    public GenreDto toDto(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDto.class);
    }
}
