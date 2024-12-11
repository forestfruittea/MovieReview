package com.example.movierev.Repositories;

import com.example.movierev.Entities.RatingEntity;
import com.example.movierev.Entities.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    public List<RatingEntity> findByMovieId(Long movieId);
    public RatingEntity save(RatingEntity ratingEntity);
    public Optional<RatingEntity> findByUserAndMovie(Long userId, Long movieId);
    public RatingEntity update(RatingEntity rating);
}
