package com.example.movierev.controller;
import com.example.movierev.dto.*;
import com.example.movierev.filter.Role;
import com.example.movierev.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/tool")
public class AdminController {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final ActorService actorService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public AdminController(MovieService movieService, DirectorService directorService, ActorService actorService, GenreService genreService, ReviewService reviewService, UserService userService) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.actorService = actorService;
        this.genreService = genreService;
        this.reviewService = reviewService;
        this.userService = userService;
    }
    @PostMapping("/movies/create")
    public String createMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String country,
            @RequestParam String releaseDate,
            @RequestParam String length,
            @RequestParam String budget,
            @RequestParam String boxOffice,
            @RequestParam List<String> actors,
            @RequestParam List<String> genres,
            @RequestParam String directorName,
            @RequestParam("posterFile") MultipartFile posterFile,
            Model model) {
        try {
            // Handle file upload
            String posterPath = Paths.get(posterFile.getOriginalFilename()).getFileName().toString();
            String uploadDir = "uploads/posters";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            posterFile.transferTo(new File(uploadPath, posterPath));
            DirectorDto directorDto = new DirectorDto();
            directorDto.setName(directorName);
            // Create Movie DTO
            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(title);
            movieDto.setDescription(description);
            movieDto.setCountry(country);
            movieDto.setReleaseDate(LocalDate.parse(releaseDate));
            movieDto.setLength(Long.parseLong(length));
            movieDto.setBudget(Long.parseLong(budget));
            movieDto.setBoxOffice(Long.parseLong(boxOffice));
            movieDto.setPosterPath(posterPath);
            movieDto.setDirector(directorDto);
            movieDto.setActors(actorService.getActorsByNames(actors));
            movieDto.setGenres(genreService.getGenresByNames(genres));

            movieService.save(movieDto);
            model.addAttribute("message", "Movie created successfully!");

            return "redirect:/admin/tool/movies/create";
        } catch (IOException e) {
            model.addAttribute("error", "Error uploading the poster: " + e.getMessage());
            return "movie-create";
        }
    }
    @PostMapping("/movies/delete")
    public String deleteMovie(@RequestParam Long movieId, Model model) {
        try {
            movieService.delete(movieId);
            model.addAttribute("message", "Movie deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting the movie: " + e.getMessage());
        }
        return "redirect:/admin/tool/movies/delete";
    }
    @PostMapping("/directors/create")
    public String createDirector(
            @RequestParam String name,
            @RequestParam String bio,
            @RequestParam String dateOfBirth,
            @RequestParam("photo") MultipartFile photoFile,
            Model model) {
        try {
            // Handle file upload
            String photoPath = Paths.get(photoFile.getOriginalFilename()).getFileName().toString();
            String uploadDir = "uploads/directors";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            photoFile.transferTo(new File(uploadPath, photoPath));

            // Create Director DTO
            DirectorDto directorDto = new DirectorDto();
            directorDto.setName(name);
            directorDto.setBio(bio);
            directorDto.setDateOfBirth(LocalDate.parse(dateOfBirth));
            directorDto.setPhotoPath(photoPath);

            directorService.save(directorDto);
            model.addAttribute("message", "Director created successfully!");

            return "redirect:/admin/tool/directors/create"; // Redirect to directors list
        } catch (IOException e) {
            model.addAttribute("error", "Error uploading photo: " + e.getMessage());
            return "director-create";
        }
    }
    @PostMapping("/directors/delete")
    public String deleteDirector(@RequestParam Long directorId, Model model) {
        try {
            directorService.delete(directorId);
            model.addAttribute("message", "Director deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting the director: " + e.getMessage());
        }
        return "redirect:/admin/tool/directors/delete"; // Redirect to director deletion page
    }
    @PostMapping("/genres/create")
    public String createGenre(
            @RequestParam String name,
            @RequestParam("image") MultipartFile imageFile,
            Model model) {
        try {
            String imagePath = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
            String uploadDir = "uploads/genreImages";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            imageFile.transferTo(new File(uploadPath, imagePath));

            GenreDto genreDto = new GenreDto();
            genreDto.setName(name);
            genreDto.setImagePath(imagePath);
            genreService.save(genreDto);
            model.addAttribute("message", "Genre created successfully!");
        } catch (IOException e) {
            model.addAttribute("error", "Error uploading file: " + e.getMessage());
            return "genre-create";
        }
        return "redirect:/admin/tool/genres/create";
    }
    @PostMapping("/genres/delete")
    public String deleteGenre(@RequestParam Long genreId, Model model) {
        try {
            genreService.delete(genreId);
            model.addAttribute("message", "Genre deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting genre: " + e.getMessage());
        }
        return "redirect:/admin/tool/genres/delete";
    }
    @PostMapping("/reviews/delete")
    public String deleteReview(@RequestParam("reviewId") Long reviewId, Model model) {
        try {
            reviewService.delete(reviewId);
            model.addAttribute("message", "Review deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Invalid review ID.");
        }
        List<ReviewDto> reviews = reviewService.findAllSortedByUsernameAndMovieTitle();
        model.addAttribute("reviews", reviews);
        return "review-delete"; // Return the same view to refresh the list
    }
    @PostMapping("/actors/delete")
    public String deleteActor(@RequestParam("actorId") Long actorId, Model model) {
        try {
            actorService.delete(actorId);
            model.addAttribute("message", "Actor deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to delete actor: " + e.getMessage());
        }
        List<ActorDto> actors = actorService.findAllSorted();
        model.addAttribute("actors", actors);
        return "actor-delete"; // Return the same view to refresh the list
    }
    @PostMapping("/users/create")
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("role") String roleString,
                             Model model) {
        // Convert the role string to Role enum
        Role role = Role.valueOf(roleString.toUpperCase());

        // Create a UserDto object
        UserDto userDto = UserDto.builder()
                .username(username)
                .password(password)
                .role(role)  // Store the role as a string in the UserDto
                .build();

        boolean success = userService.registerUser(userDto);

        if (success) {
            return "redirect:/admin/tool/users/create"; // Redirect to the same page on success
        } else {
            model.addAttribute("error", "Username is already taken or there was an error during registration.");
            return "user-create"; // Return to the form with error message
        }
    }
    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("userId") Long userId, Model model) {
        try {
            userService.delete(userId);
            model.addAttribute("message", "User deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Invalid user ID.");
        }
        List<UserDto> users = userService.findAllSorted();
        model.addAttribute("users", users);
        return "user-delete"; // Return the same view to refresh the list
    }
    @PostMapping("/actors/create")
    public String createActor(@RequestParam("name") String name,
                              @RequestParam("bio") String bio,
                              @RequestParam("yearOfBirth") Long yearOfBirth,
                              @RequestParam("height") Long height,
                              @RequestParam("photoPath") MultipartFile photo,
                              Model model) {
        try {
            // Set photo upload path
            String uploadPath = "static/uploads/persons/"; // Replace with the actual path
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save the uploaded file
            String photoPath = Paths.get(photo.getOriginalFilename()).getFileName().toString();
            File photoFile = new File(uploadPath + File.separator + photoPath);
            photo.transferTo(photoFile);

            // Create ActorDto
            ActorDto actorDto = ActorDto.builder()
                    .name(name)
                    .bio(bio)
                    .yearOfBirth(yearOfBirth)
                    .height(height)
                    .photoPath(photoPath)
                    .build();

            // Save actor using service
            actorService.save(actorDto);

            model.addAttribute("message", "Actor created successfully!");
            return "redirect:/admin/tool/actors/create"; // Redirect to the actor creation page after success

        } catch (Exception e) {
            model.addAttribute("error", "Failed to create actor: " + e.getMessage());
            return "actor-create"; // Return to form on error
        }
    }
    @GetMapping
    public String showAdminTool() {
        // Return the name of the view to be rendered
        return "admin-tool";  // This will resolve to /WEB-INF/admin-tool.jsp
    }
    @GetMapping("/genres/delete")
    public String deleteGenreForm(Model model) {
        List<GenreDto> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genre-delete"; // Corresponds to /WEB-INF/genre-delete.jsp
    }
    @GetMapping("/movies/delete")
    public String showDeleteMoviesPage(Model model) {
        List<MovieDto> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movie-delete";
    }
    @GetMapping("/actors/delete")
    public String showActorDeletionPage(Model model) {
        // Fetch all actors and add to the model
        List<ActorDto> actors = actorService.findAllSorted();
        model.addAttribute("actors", actors);
        return "actor-delete"; // Resolve to /WEB-INF/actor-delete.jsp
    }
    @GetMapping("/users/delete")
    public String showUserDeletionPage(Model model) {
        // Fetch all users
        List<UserDto> users = userService.findAllSorted();
        model.addAttribute("users", users);
        return "user-delete"; // This will resolve to /WEB-INF/user-delete.jsp
    }
    @GetMapping("/directors/delete")
    public String showDeleteDirectorsPage(Model model) {
        List<DirectorDto> directors = directorService.findAll();
        model.addAttribute("directors", directors);
        return "director-delete"; // Corresponds to /WEB-INF/director-delete.jsp
    }
    @GetMapping("/reviews/delete")
    public String showReviewDeletionPage(Model model) {
        // Fetch all reviews, sorted by username and movie title
        List<ReviewDto> reviews = reviewService.findAllSortedByUsernameAndMovieTitle();
        model.addAttribute("reviews", reviews);
        return "review-delete"; // This will resolve to /WEB-INF/review-delete.jsp
    }
    @GetMapping("/users/create")
    public String showUserCreateForm() {
        // Forward to user-create.jsp to render the user creation form
        return "user-create";
    }
    @GetMapping("/genres/create")
    public String createGenreForm(Model model) {
        return "genre-create"; // Corresponds to /WEB-INF/genre-create.jsp
    }
    @GetMapping("/movies/create")
    public String showCreateMovieForm(Model model) {
        List<DirectorDto> directorDtos = directorService.findAll();
        List<GenreDto> genreDtos = genreService.findAll();
        List<ActorDto> actorDtos = actorService.findAll();

        model.addAttribute("directors", directorDtos);
        model.addAttribute("genres", genreDtos);
        model.addAttribute("actors", actorDtos);

        return "movie-create";
    }
    @GetMapping("/directors/create")
    public String showCreateDirectorForm() {
        return "director-create";
    }
    @GetMapping("/actors/create")
    public String showActorCreateForm() {
        // Render the actor creation form
        return "actor-create"; // Resolve to /WEB-INF/actor-create.jsp
    }

}


