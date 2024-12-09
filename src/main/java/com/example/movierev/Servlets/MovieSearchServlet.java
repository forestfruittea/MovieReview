package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Repositories.MovieRepository;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/movies/search")
public class MovieSearchServlet extends HttpServlet {

    @Inject
    private MovieService movieService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch all movies
        List<MovieDto> movies = movieService.findAllSorted();

        req.setAttribute("movies", movies);

        req.getRequestDispatcher("/WEB-INF/movies-search.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String movieIdParam = req.getParameter("movieId");
            if (movieIdParam != null) {
                try {
                    Long movieId = Long.parseLong(movieIdParam);
                    movieService.delete(movieId);

                    // Set a success message
                    req.setAttribute("message", "Movie deleted successfully!");
                } catch (NumberFormatException e) {
                    // Handle invalid movie ID format
                    req.setAttribute("message", "Invalid movie ID.");
                }
            }
        List<MovieDto> movies = movieService.findAllSorted();

        req.setAttribute("movies", movies);

        req.getRequestDispatcher("/WEB-INF/movies-search.jsp").forward(req, resp);
    }
}


