package com.example.movierev.controller;


import com.example.movierev.dto.MovieDto;
import com.example.movierev.dto.RatingDto;
import com.example.movierev.dto.ReviewDto;
import com.example.movierev.dto.UserDto;
import com.example.movierev.service.MovieService;
import com.example.movierev.service.RatingService;
import com.example.movierev.service.ReviewService;
import com.example.movierev.service.UserService;
import com.example.movierev.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final RatingService ratingService;

    public MovieController(MovieService movieService, ReviewService reviewService, UserService userService, RatingService ratingService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/movies")
    public String listMovies(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 2;
        List<MovieDto> movies = movieService.findAll();

        for (MovieDto movie : movies) {
            Double averageRating = ratingService.calculateAverageRating(movie.getId());
            movie.setAverageRating(averageRating);
        }

        long totalMovies = movieService.count();
        int totalPages = (int) Math.ceil((double) totalMovies / pageSize);

        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "movies"; // Corresponds to movies.jsp
    }

    @GetMapping("/movie")
    public String movieDetails(@RequestParam(name = "id") Long id, Model model) {
        Optional<MovieDto> movieOptional = movieService.findById(id);

        if (movieOptional.isPresent()) {
            MovieDto movie = movieOptional.get();
            List<ReviewDto> reviews = reviewService.findAllForMovie(id);
            Double averageRating = ratingService.calculateAverageRating(id);
            Long userId = SecurityUtil.getLoggedInUserId();
            Optional<RatingDto> userRating = ratingService.findByUserAndMovie(userId, id);

            model.addAttribute("movie", movie);
            model.addAttribute("reviews", reviews);
            model.addAttribute("averageRating", averageRating);
            model.addAttribute("userRating", userRating.orElse(null));
            return "movie-details"; // Corresponds to movie-details.jsp
        } else {
            model.addAttribute("error", "Movie not found");
            return "error"; // Error page
        }
    }
}

