package com.example.movierev.Services;

import com.example.movierev.DTOs.ReviewDto;

import java.util.List;

public interface ReviewService {
    void save(ReviewDto reviewDto);
    List<ReviewDto> findAllForMovie(Long movieId);
    public List<ReviewDto> findAllForUser(Long userId);
}
