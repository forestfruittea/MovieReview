package com.example.movierev.mapper.impl;

import com.example.movierev.dto.RatingDto;
import com.example.movierev.entity.RatingEntity;
import com.example.movierev.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper implements Mapper<RatingEntity, RatingDto> {
    private final ModelMapper modelMapper;
    @Autowired
    public RatingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RatingEntity toEntity(RatingDto ratingDto) {
        return modelMapper.map(ratingDto, RatingEntity.class);
    }

    @Override
    public RatingDto toDto(RatingEntity ratingEntity) {
        return modelMapper.map(ratingEntity, RatingDto.class);
    }
}
