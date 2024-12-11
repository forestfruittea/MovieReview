package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Services.MovieService;
import com.example.movierev.Services.RatingService;
import com.example.movierev.Services.ReviewService;
import com.example.movierev.Services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/movie")
public class MovieDetailsServlet extends HttpServlet {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final RatingService ratingService;


    @Inject
    public MovieDetailsServlet(MovieService movieService, ReviewService reviewService, UserService userService, RatingService ratingService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.ratingService = ratingService;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        String ratingParam = request.getParameter("rating"); // Get rating parameter

        String movieIdParam = request.getParameter("id");
        if (movieIdParam == null || content == null || content.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid review data");
            return;
        }

        try {
            Long movieId = Long.parseLong(movieIdParam);

            Long userId = userService.getLoggedInUserId(request);

            Optional<UserDto> userOptional = userService.findById(userId);
            Optional<MovieDto> movieDtoOptional = movieService.findById(movieId);

            if (userOptional.isPresent()&&movieDtoOptional.isPresent()) {
                UserDto userDto = userOptional.get();
                MovieDto movieDto = movieDtoOptional.get();

                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setContent(content);
                reviewDto.setUser(userDto);
                reviewDto.setMovie(movieDto);

                reviewService.save(reviewDto);

                doGet(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID format");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = request.getParameter("id");

        if (movieId != null) {
            try {
                Long id = Long.parseLong(movieId);

                Optional<MovieDto> optionalMovie = movieService.findById(id);
                List<ReviewDto> reviewDtoList = reviewService.findAllForMovie(id);

                if (optionalMovie.isPresent()) {

                    request.setAttribute("movie", optionalMovie.get());
                    request.setAttribute("reviews",reviewDtoList);
                    request.getRequestDispatcher("/WEB-INF/movie-details.jsp").forward(request, response);

                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie ID is required");
        }
    }

}
