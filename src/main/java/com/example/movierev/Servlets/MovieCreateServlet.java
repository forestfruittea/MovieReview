package com.example.movierev.Servlets;

import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.DTOs.DirectorDto;
import com.example.movierev.DTOs.GenreDto;
import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.Services.*;
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

@WebServlet("/admin/tool/movies/create")
public class MovieCreateServlet extends HttpServlet {
    private final MovieService movieService;
    private final DirectorService directorService;
    private final ActorService actorService;
    private final GenreService genreService;
    @Inject
    public MovieCreateServlet(MovieService movieService, DirectorService directorService, ActorService actorService, GenreService genreService) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.actorService = actorService;
        this.genreService = genreService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DirectorDto> directorDtos = directorService.findAll();
        List<GenreDto> genreDtos = genreService.findAll();
        List<ActorDto> actorDtos = actorService.findAll();

        req.setAttribute("actors", actorDtos);
        req.setAttribute("genres", genreDtos);
        req.setAttribute("directors", directorDtos);

        req.getRequestDispatcher("/WEB-INF/movie-create.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {

            MovieDto movieDto = mapper.readValue(req.getInputStream(), MovieDto.class);

            Validator validator;
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                validator = factory.getValidator();
            }
            Set<ConstraintViolation<MovieDto>> violations = validator.validate(movieDto);

            if (!violations.isEmpty()) {

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
            movieService.save(movieDto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Movie created successfully.\"}");

        }  catch (JsonProcessingException e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid JSON format.\"}");
        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
        resp.sendRedirect("/admin/movies/create ");
    }

}
