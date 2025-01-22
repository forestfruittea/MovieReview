package com.example.movierev.repository;

import com.example.movierev.entity.RatingEntity;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    List<RatingEntity> findByMovieId(Long movieId);
    RatingEntity save(RatingEntity ratingEntity);
    Optional<RatingEntity> findByUserAndMovie(Long userId, Long movieId);
    List<RatingEntity> findByUser(Long userId);
    RatingEntity update(RatingEntity rating);
}
