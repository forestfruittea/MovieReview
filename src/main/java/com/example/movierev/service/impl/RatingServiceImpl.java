package com.example.movierev.service.impl;

import com.example.movierev.dto.RatingDto;
import com.example.movierev.entity.RatingEntity;
import com.example.movierev.mapper.impl.RatingMapper;
import com.example.movierev.repository.RatingRepository;
import com.example.movierev.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    // Find rating by user and movie
    @Override
    public Optional<RatingDto> findByUserAndMovie(Long userId, Long movieId) {
        Optional<RatingEntity> ratingEntity = ratingRepository.findByUserIdAndMovieId(userId, movieId);
        log.debug("finds rate by user id and movie id");
        return ratingEntity.map(RatingDto::of);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingDto> findByUser(Long userId) {
        List<RatingEntity> ratingEntities = ratingRepository.findByUserId(userId);
        log.debug("finds rate by user id");

        return ratingEntities.stream()
                .map(RatingDto::of)
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
        ratingEntity = ratingRepository.save(ratingEntity);
        log.debug("updates rate");
        return ratingMapper.toDto(ratingEntity);
    }
}
