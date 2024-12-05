package com.example.movierev.Services;

import com.example.movierev.DTOs.ReviewDto;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewDto reviewDto);
    List<ReviewDto> getReviewsForMovie(Long movieId);
}
