package com.example.movierev.service;

import com.example.movierev.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void save(ReviewDto reviewDto);
    List<ReviewDto> findAllForMovie(Long movieId);
    List<ReviewDto> findAllForUser(Long userId);
    List<ReviewDto> findAllSortedByUsernameAndMovieTitle();
    void delete(Long reviewId);

    //TODO ReviewDto update(ReviewDto reviewDto);

}
