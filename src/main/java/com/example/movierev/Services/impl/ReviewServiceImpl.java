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
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import com.example.movierev.DTOs.UserDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Stateless
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    @Inject
    public ReviewServiceImpl(UserRepository userRepository, MovieRepository movieRepository, ReviewRepository reviewRepository, ReviewMapper reviewMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void save(ReviewDto reviewDto) {
        Optional<MovieEntity> movieOptional = movieRepository.findById(reviewDto.getMovie().getId());
        if (movieOptional.isEmpty()) {
            throw new IllegalArgumentException("Movie not found");
        }
        MovieEntity movieEntity = movieOptional.get();
        UserDto userDto = reviewDto.getUser();

        Optional<UserEntity> userOptional = userRepository.findById(userDto.getId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        UserEntity userEntity = userOptional.get();

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUser(userEntity);
        reviewEntity.setCreatedAt(LocalDateTime.now());
        reviewEntity.setMovie(movieEntity);
        reviewEntity.setContent(reviewDto.getContent());
        reviewRepository.save(reviewEntity);
        }
    @Override
    public void delete(Long reviewId) {
        reviewRepository.delete(reviewId);
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ReviewDto> findAllForMovie(Long movieId) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByMovieId(movieId);

        return reviewEntities.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ReviewDto> findAllForUser(Long userId) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByUserId(userId);

        return reviewEntities.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ReviewDto> findAllSortedByUsernameAndMovieTitle() {
        return reviewRepository.findAll()
                .stream()
                .sorted(Comparator.comparing((ReviewEntity review) ->
                                review.getUser() != null ? review.getUser().getUsername() : "")
                        .thenComparing(review ->
                                review.getMovie() != null ? review.getMovie().getTitle() : ""))
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

}
