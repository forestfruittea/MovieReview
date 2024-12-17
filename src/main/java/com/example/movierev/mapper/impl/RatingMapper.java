package com.example.movierev.mapper.impl;

import com.example.movierev.dto.RatingDto;
import com.example.movierev.entity.RatingEntity;
import com.example.movierev.mapper.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

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
