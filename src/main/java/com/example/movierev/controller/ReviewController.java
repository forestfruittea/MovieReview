package com.example.movierev.controller;

import com.example.movierev.dto.MovieDto;
import com.example.movierev.dto.ReviewDto;
import com.example.movierev.dto.UserDto;
import com.example.movierev.service.MovieService;
import com.example.movierev.service.ReviewService;
import com.example.movierev.service.UserService;
import com.example.movierev.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final MovieService movieService;

    public ReviewController(ReviewService reviewService, UserService userService, MovieService movieService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public String addReview(@RequestParam Long movieId, @RequestParam String content) {
        Long userId = SecurityUtil.getLoggedInUserId();
        Optional<UserDto> userOptional = userService.findById(userId);
        Optional<MovieDto> movieOptional = movieService.findById(movieId);

        if (userOptional.isPresent() && movieOptional.isPresent()) {
            ReviewDto review = new ReviewDto();
            review.setContent(content);
            review.setUser(userOptional.get());
            review.setMovie(movieOptional.get());
            reviewService.save(review);
        }

        return "redirect:/movie?id=" + movieId;
    }
}
