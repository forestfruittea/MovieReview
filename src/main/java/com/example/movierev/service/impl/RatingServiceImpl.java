package com.example.movierev.service.impl;

import com.example.movierev.dto.RatingDto;
import com.example.movierev.entity.RatingEntity;
import com.example.movierev.mapper.impl.RatingMapper;
import com.example.movierev.repository.RatingRepository;
import com.example.movierev.service.RatingService;
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
