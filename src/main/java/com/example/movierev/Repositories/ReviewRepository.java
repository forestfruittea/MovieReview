package com.example.movierev.Repositories;

import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Entities.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    ReviewEntity createReview(ReviewEntity reviewEntity);
    public List<ReviewEntity> findByMovieId(Long movieId);

    ReviewEntity updateReview(ReviewEntity reviewEntity);

    void deleteReview(Long reviewId);

    Optional<ReviewEntity> findReviewById(Long reviewId);
    List<ReviewEntity> findAllReviews();
}
