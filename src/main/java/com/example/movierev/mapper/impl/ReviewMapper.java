package com.example.movierev.mapper.impl;

import com.example.movierev.dto.ReviewDto;
import com.example.movierev.entity.ReviewEntity;
import com.example.movierev.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Mapper<ReviewEntity, ReviewDto> {

    private final ModelMapper modelMapper;
    @Autowired
    public ReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewEntity toEntity(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, ReviewEntity.class);
    }

    @Override
    public ReviewDto toDto(ReviewEntity reviewEntity) {
        return modelMapper.map(reviewEntity, ReviewDto.class);
    }
}

