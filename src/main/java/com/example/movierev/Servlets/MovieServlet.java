package com.example.movierev.Servlets;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Mappers.impl.DirectorMapper;
import com.example.movierev.Services.ActorService;
import com.example.movierev.Services.DirectorService;
import com.example.movierev.Services.GenreService;
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
import java.util.List;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final GenreService genreService;
    private final ActorService actorService;
    @Inject
    public MovieServlet(MovieService movieService, DirectorService directorService, GenreService genreService, ActorService actorService, DirectorMapper directorMapper) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.genreService = genreService;
        this.actorService = actorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDto> movies = movieService.getAllMovies();
        for (MovieDto movie : movies) {
            if (movie.getPosterPath() != null && !movie.getPosterPath().isEmpty()) {
                movie.setPosterPath("/MovieRev-1.0-SNAPSHOT/uploads/posters/" + movie.getPosterPath());
            }
        }
        System.out.println(movies);
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/movies.jsp").forward(req, resp);  // assuming you have a JSP to display movies

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Parse JSON body into MovieDto
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            MovieDto movieDto = mapper.readValue(req.getInputStream(), MovieDto.class);

            // Call the service to create a movie
            movieService.createMovie(movieDto);

            // Send a success response
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Movie created successfully.\"}");
        } catch (Exception e) {
            // Handle errors and send error response
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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

