package com.example.movierev.repository;

import com.example.movierev.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    ReviewEntity save(ReviewEntity reviewEntity);
    List<ReviewEntity> findByMovieId(Long movieId);
    List<ReviewEntity> findByUserId(Long userId);
    void delete(Long reviewId);
    Optional<ReviewEntity> findById(Long reviewId);
    List<ReviewEntity> findAll();
//TODO    ReviewEntity update(ReviewEntity reviewEntity);
}
