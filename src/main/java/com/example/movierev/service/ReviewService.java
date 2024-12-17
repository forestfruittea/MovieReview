package com.example.movierev.service;

import com.example.movierev.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void save(ReviewDto reviewDto);
    List<ReviewDto> findAllForMovie(Long movieId);
    public List<ReviewDto> findAllForUser(Long userId);
    public List<ReviewDto> findAllSortedByUsernameAndMovieTitle();
    public void delete(Long reviewId);
}
