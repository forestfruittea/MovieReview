package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.RatingDto;
import com.example.movierev.Entities.RatingEntity;
import com.example.movierev.Mappers.impl.RatingMapper;
import com.example.movierev.Repositories.RatingRepository;
import com.example.movierev.Services.RatingService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Slf4j
public class RatingServiceImpl implements RatingService {

    @Inject
    private RatingRepository ratingRepository;

    @Inject
    private RatingMapper ratingMapper;

    // Find rating by user and movie
    @Override
    public Optional<RatingDto> findByUserAndMovie(Long userId, Long movieId) {
        Optional<RatingEntity> ratingEntity = ratingRepository.findByUserAndMovie(userId, movieId);
        log.debug("finds rate by user id and movie id");
        return ratingEntity.map(ratingMapper::toDto);
    }

    @Override
    public List<RatingDto> findByUser(Long userId) {
        List<RatingEntity> ratingEntities = ratingRepository.findByUser(userId);
        log.debug("finds rate by user id");

        return ratingEntities.stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RatingDto save(RatingDto ratingDto) {
        RatingEntity ratingEntity = ratingMapper.toEntity(ratingDto);
        ratingEntity = ratingRepository.save(ratingEntity);
        log.debug("saves rate");

        return ratingMapper.toDto(ratingEntity);
    }

    @Override
    public double calculateAverageRating(Long movieId) {
        List<RatingEntity> ratings = ratingRepository.findByMovieId(movieId);
        log.debug("calculates average rating for movie");

        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream().mapToDouble(RatingEntity::getRating).average().orElse(0.0);
    }
    @Override
    public RatingDto update(RatingDto ratingDto) {
        RatingEntity ratingEntity = ratingMapper.toEntity(ratingDto);
        ratingEntity = ratingRepository.update(ratingEntity);
        log.debug("updates rate");

        return ratingMapper.toDto(ratingEntity);
    }
}
