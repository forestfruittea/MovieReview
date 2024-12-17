package com.example.movierev.repository;

import com.example.movierev.entity.RatingEntity;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    public List<RatingEntity> findByMovieId(Long movieId);
    public RatingEntity save(RatingEntity ratingEntity);
    public Optional<RatingEntity> findByUserAndMovie(Long userId, Long movieId);
    public List<RatingEntity> findByUser(Long userId);
    public RatingEntity update(RatingEntity rating);
}
