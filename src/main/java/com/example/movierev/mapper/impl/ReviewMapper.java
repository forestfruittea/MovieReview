package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.Entities.ReviewEntity;
import com.example.movierev.Mappers.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ReviewMapper implements Mapper<ReviewEntity, ReviewDto> {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public ReviewEntity toEntity(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, ReviewEntity.class);
    }

    @Override
    public ReviewDto toDto(ReviewEntity reviewEntity) {
        return modelMapper.map(reviewEntity, ReviewDto.class);
    }
}

