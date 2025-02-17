package com.example.movierev.service;

import com.example.movierev.dto.RatingDto;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    double calculateAverageRating(Long movieId);
    RatingDto save(RatingDto ratingDto);
    Optional<RatingDto> findByUserAndMovie(Long userId, Long movieId);
    List<RatingDto> findByUser(Long userId);
    RatingDto update(RatingDto ratingDto);
}
