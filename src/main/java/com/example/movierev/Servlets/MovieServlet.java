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
        for (MovieDto movie : movies) {
            if (movie.getPosterPath() != null && !movie.getPosterPath().isEmpty()) {
                movie.setPosterPath("/MovieRev-1.0-SNAPSHOT/uploads/posters/" + movie.getPosterPath());
            }
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/movies.jsp").forward(req, resp);  // assuming you have a JSP to display movies

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MovieDto movieRequest = objectMapper.readValue(req.getReader(), MovieDto.class);

        // Ensure proper date format

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movieRequest.getTitle());
        movieDto.setDescription(movieRequest.getDescription());
        movieDto.setGenre(movieRequest.getGenre());
        movieDto.setReleaseDate(movieRequest.getReleaseDate());
        movieDto.setPosterPath(movieRequest.getPosterPath());
        movieDto.setCountry(movieRequest.getCountry());
        movieDto.setDirector(movieRequest.getDirector());
        movieDto.setStarringRole(movieRequest.getStarringRole());
        movieDto.setLength(movieRequest.getLength());
        movieDto.setBudget(movieRequest.getBudget());
        movieDto.setBoxOffice(movieRequest.getBoxOffice());

        movieService.createMovie(movieDto);

        resp.getWriter().write("Movie created successfully!");
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long movieId = objectMapper.readValue(req.getReader(), Long.class); // Assuming the body contains just the movieId as a number

        // Call the MovieService to delete the movie
        movieService.deleteMovie(movieId);

    }
}

