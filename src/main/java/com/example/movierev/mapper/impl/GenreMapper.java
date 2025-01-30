package com.example.movierev.mapper.impl;

import com.example.movierev.dto.GenreDto;
import com.example.movierev.entity.GenreEntity;
import com.example.movierev.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements Mapper<GenreEntity, GenreDto> {

    private final ModelMapper modelMapper;
    @Autowired
    public GenreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreEntity toEntity(GenreDto genreDto) {
        return modelMapper.map(genreDto, GenreEntity.class);
    }

    @Override
    public GenreDto toDto(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDto.class);
    }
}
