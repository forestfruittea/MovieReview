package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Entities.ReviewEntity;
import com.example.movierev.Entities.UserEntity;
import com.example.movierev.Mappers.impl.ReviewMapper;
import com.example.movierev.Mappers.impl.UserMapper;
import com.example.movierev.Repositories.MovieRepository;
import com.example.movierev.Repositories.ReviewRepository;
import com.example.movierev.Repositories.UserRepository;
import com.example.movierev.Services.ReviewService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import com.example.movierev.DTOs.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;
    @Inject
    public ReviewServiceImpl(UserRepository userRepository, MovieRepository movieRepository, ReviewRepository reviewRepository, ReviewMapper reviewMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void addReview(ReviewDto reviewDto) {
        Optional<MovieEntity> movieOptional = movieRepository.findMovieById(reviewDto.getMovieId());
        if (movieOptional.isEmpty()) {
            throw new IllegalArgumentException("Movie not found");
        }
        MovieEntity movieEntity = movieOptional.get();
        UserDto userDto = reviewDto.getUser();

        Optional<UserEntity> userOptional = userRepository.findUserById(userDto.getId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        UserEntity userEntity = userOptional.get();

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUser(userEntity);
        reviewEntity.setCreatedAt(LocalDateTime.now());
        reviewEntity.setMovie(movieEntity);
        reviewEntity.setContent(reviewDto.getContent());

        // Persist the review entity
        reviewRepository.createReview(reviewEntity);
        }

    @Override
    public List<ReviewDto> getReviewsForMovie(Long movieId) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByMovieId(movieId);

        // Convert ReviewEntity list to ReviewDto list
        return reviewEntities.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
}
