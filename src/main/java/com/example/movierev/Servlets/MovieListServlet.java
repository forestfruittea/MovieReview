package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.Mappers.impl.DirectorMapper;
import com.example.movierev.Services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {

    private final MovieService movieService;
    private final RatingService ratingService;

    @Inject
    public MovieListServlet(MovieService movieService, RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDto> movies = movieService.findAll();
        for (MovieDto movie : movies) {
            Double averageRating = ratingService.calculateAverageRating(movie.getId());
            movie.setAverageRating(averageRating);  // Add this line if averageRating is not already part of MovieDto
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/movies.jsp").forward(req, resp);

    }





    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long movieId = objectMapper.readValue(req.getReader(), Long.class);

        movieService.delete(movieId);

    }
}

