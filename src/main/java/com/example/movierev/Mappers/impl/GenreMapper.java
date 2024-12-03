package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.GenreDto;
import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Mappers.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class GenreMapper implements Mapper<GenreEntity, GenreDto> {
    @Inject
    private ModelMapper modelMapper;
//    @Inject
//    public GenreMapper(ModelMapper modelMapper){
//        this.modelMapper = modelMapper;
//    }
    @Override
    public GenreEntity toEntity(GenreDto genreDto) {
        return modelMapper.map(genreDto, GenreEntity.class);
    }

    @Override
    public GenreDto toDto(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDto.class);
    }
}
