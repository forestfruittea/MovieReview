package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.RatingDto;
import com.example.movierev.Entities.RatingEntity;
import com.example.movierev.Mappers.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.Map;
@ApplicationScoped
public class RatingMapper implements Mapper<RatingEntity, RatingDto> {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public RatingEntity toEntity(RatingDto ratingDto) {
        return modelMapper.map(ratingDto, RatingEntity.class);
    }

    @Override
    public RatingDto toDto(RatingEntity ratingEntity) {
        return modelMapper.map(ratingEntity, RatingDto.class);
    }
}
