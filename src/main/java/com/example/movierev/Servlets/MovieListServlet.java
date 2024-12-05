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
    private final DirectorService directorService;
    private final GenreService genreService;
    private final ActorService actorService;
    @Inject
    public MovieListServlet(MovieService movieService, DirectorService directorService, GenreService genreService, ActorService actorService, DirectorMapper directorMapper) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.genreService = genreService;
        this.actorService = actorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDto> movies = movieService.getAllMovies();
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/movies.jsp").forward(req, resp);  // assuming you have a JSP to display movies

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            // Parse JSON body into MovieDto
            MovieDto movieDto = mapper.readValue(req.getInputStream(), MovieDto.class);

            Validator validator;
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                validator = factory.getValidator();
            }
            Set<ConstraintViolation<MovieDto>> violations = validator.validate(movieDto);

            if (!violations.isEmpty()) {
                // If there are validation violations, send a BAD REQUEST with error details
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<MovieDto> violation : violations) {
                    errorMessage.append(violation.getPropertyPath())
                            .append(": ")
                            .append(violation.getMessage())
                            .append("\n");
                }

                resp.setContentType("application/json");
                resp.getWriter().write("{\"error\":\"" + errorMessage.toString().trim() + "\"}");
                return;
            }
            // Call the service to create a movie
            movieService.createMovie(movieDto);

            // Send a success response
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Movie created successfully.\"}");

        }  catch (JsonProcessingException e) {
            // Handle JSON parsing errors
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid JSON format.\"}");
        } catch (Exception e) {
            // Handle unexpected errors
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long movieId = objectMapper.readValue(req.getReader(), Long.class); // Assuming the body contains just the movieId as a number

        // Call the MovieService to delete the movie
        movieService.deleteMovie(movieId);

    }
}
