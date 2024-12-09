package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Mappers.impl.DirectorMapper;
import com.example.movierev.Services.ActorService;
import com.example.movierev.Services.DirectorService;
import com.example.movierev.Services.GenreService;
import com.example.movierev.Services.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {

    private final MovieService movieService;

    @Inject
    public MovieListServlet(MovieService movieService, DirectorService directorService, GenreService genreService, ActorService actorService, DirectorMapper directorMapper) {
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDto> movies = movieService.findAll();
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

