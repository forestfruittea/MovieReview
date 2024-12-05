package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Services.MovieService;
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
    @Inject
    private ReviewService reviewService;
    @Inject
    private UserService userService;

    @Inject
    public MovieDetailsServlet(MovieService movieService) {
        this.movieService = movieService;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the review content from the form data
        String content = request.getParameter("content");


        // Get the movieId from the form data
        String movieIdParam = request.getParameter("id");
        if (movieIdParam == null || content == null || content.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid review data");
            return;
        }

        try {
            Long movieId = Long.parseLong(movieIdParam);

            // Get the current logged-in user ID
            Long userId = getLoggedInUserId(request);

            // Fetch the UserDto from the UserService
            Optional<UserDto> userOptional = userService.getUserById(userId);

            if (userOptional.isPresent()) {
                UserDto userDto = userOptional.get();

                // Create and populate the ReviewDto
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setContent(content);
                reviewDto.setUser(userDto);
                reviewDto.setMovieId(movieId);

                // Add the review
                reviewService.addReview(reviewDto);

                // Refresh movie details and reviews
                doGet(request, response); // Forward the updated data to the JSP
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

                // Get the movie from the service
                Optional<MovieDto> optionalMovie = movieService.getMovieById(id);
                List<ReviewDto> reviewDtoList = reviewService.getReviewsForMovie(id);

                if (optionalMovie.isPresent()) {

                    // Movie found, forward to the JSP
                    request.setAttribute("movie", optionalMovie.get());
                    request.setAttribute("reviews",reviewDtoList);
                    request.getRequestDispatcher("/WEB-INF/movie-details.jsp").forward(request, response);

                } else {
                    // Movie not found
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                }
            } catch (NumberFormatException e) {
                // Invalid ID format
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID format");
            }
        } else {
            // Missing ID

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie ID is required");
        }
    }
    private Long getLoggedInUserId(HttpServletRequest req) {
        // Assuming you're storing the logged-in user in session after they log in
        Long user = 2L;
        return user;
    }
}
