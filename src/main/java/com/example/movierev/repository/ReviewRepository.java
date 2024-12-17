package com.example.movierev.repository;

import com.example.movierev.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    ReviewEntity save(ReviewEntity reviewEntity);
    public List<ReviewEntity> findByMovieId(Long movieId);
    public List<ReviewEntity> findByUserId(Long userId);

    ReviewEntity update(ReviewEntity reviewEntity);

    void delete(Long reviewId);

    Optional<ReviewEntity> findById(Long reviewId);
    List<ReviewEntity> findAll();
}
