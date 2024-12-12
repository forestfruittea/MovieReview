package com.example.movierev.Services;

import com.example.movierev.DTOs.RatingDto;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    public double calculateAverageRating(Long movieId);
    public RatingDto save(RatingDto ratingDto);
    public Optional<RatingDto> findByUserAndMovie(Long userId, Long movieId);
    public List<RatingDto> findByUser(Long userId);
    public RatingDto update(RatingDto ratingDto);
}
