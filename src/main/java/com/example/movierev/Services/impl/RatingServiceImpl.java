package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.RatingDto;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Entities.RatingEntity;
import com.example.movierev.Mappers.impl.RatingMapper;
import com.example.movierev.Repositories.RatingRepository;
import com.example.movierev.Services.RatingService;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class RatingServiceImpl implements RatingService {

    @Inject
    private RatingRepository ratingRepository;

    @Inject
    private RatingMapper ratingMapper;

    // Find rating by user and movie
    @Override
    public Optional<RatingDto> findByUserAndMovie(Long userId, Long movieId) {
        Optional<RatingEntity> ratingEntity = ratingRepository.findByUserAndMovie(userId, movieId);
        return ratingEntity.map(ratingMapper::toDto);
    }

    // Save or update rating
    @Override
    public RatingDto save(RatingDto ratingDto) {
        RatingEntity ratingEntity = ratingMapper.toEntity(ratingDto);
        ratingEntity = ratingRepository.save(ratingEntity);
        return ratingMapper.toDto(ratingEntity);  // Return the saved/updated rating
    }

    // Delete a rating


    // Calculate average rating for a movie
    @Override
    public double calculateAverageRating(Long movieId) {
        List<RatingEntity> ratings = ratingRepository.findByMovieId(movieId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream().mapToDouble(RatingEntity::getRating).average().orElse(0.0);
    }
}
