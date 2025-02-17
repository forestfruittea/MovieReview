package com.example.movierev.controller;

import com.example.movierev.dto.MovieDto;
import com.example.movierev.dto.RatingDto;
import com.example.movierev.dto.UserDto;
import com.example.movierev.service.MovieService;
import com.example.movierev.service.RatingService;
import com.example.movierev.service.UserService;
import com.example.movierev.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;
    private final MovieService movieService;

    public RatingController(RatingService ratingService, UserService userService, MovieService movieService) {
        this.ratingService = ratingService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @PostMapping("/rate")
    public String rateMovie(@RequestParam Long movieId, @RequestParam Integer rating) {
        Long userId = SecurityUtil.getLoggedInUserId();
        Optional<UserDto> userOptional = userService.findById(userId);
        Optional<MovieDto> movieOptional = movieService.findById(movieId);

        if (userOptional.isPresent() && movieOptional.isPresent()) {
            Optional<RatingDto> existingRating = ratingService.findByUserAndMovie(userId, movieId);
            if (existingRating.isPresent()) {
                RatingDto updatedRating = existingRating.get();
                updatedRating.setRating(rating);
                ratingService.update(updatedRating);
            } else {
                RatingDto newRating = new RatingDto();
                newRating.setRating(rating);
                newRating.setUser(userOptional.get());
                newRating.setMovie(movieOptional.get());
                ratingService.save(newRating);
            }
        }

        return "redirect:/movie?id=" + movieId;
    }
}
