package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {

    private final MovieService movieService;
    @Inject
    public MovieServlet(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDto> movies = movieService.getAllMovies();
        req.setAttribute("movies", movies);
        System.out.println(movies);
        req.getRequestDispatcher("/WEB-INF/movies.jsp").forward(req, resp);  // assuming you have a JSP to display movies

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MovieDto movieRequest = objectMapper.readValue(req.getReader(), MovieDto.class);

        String title = movieRequest.getTitle();
        String description = movieRequest.getDescription();
        String genre = movieRequest.getGenre();
        String releaseDateStr = String.valueOf(movieRequest.getReleaseDate());

        LocalDate releaseDate = LocalDate.parse(releaseDateStr);  // Ensure proper date format

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(title);
        movieDto.setDescription(description);
        movieDto.setGenre(genre);
        movieDto.setReleaseDate(releaseDate);

        movieService.createMovie(movieDto);

        resp.getWriter().write("Movie created successfully!");
    }
}
