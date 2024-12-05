package com.example.movierev.DTOs;




import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Entities.GenreEntity;
import com.example.movierev.Entities.ReviewEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;
    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;
    private String country;
    @Valid
    private DirectorDto director;
    @Valid
    private List<ActorDto> actors;
    @Valid
    private List<GenreDto> genres;
    private LocalDate releaseDate;
    @NotNull(message = "Poster path cannot be null.")
    @Size(min = 1, max = 255, message = "Poster path must be between 1 and 255 characters")
    private String posterPath;
    @Positive(message = "Length must be a positive number")
    private Long length;
    @Positive(message = "Budget must be a positive number")
    private Long budget;
    @Positive(message = "Box office must be a positive number")
    private Long boxOffice;
    private List<ReviewEntity> reviews;

    public String getFullPosterPath() {
        String basePath = ResourceBundle.getBundle("application").getString("base.poster.path");
        if (posterPath != null && !posterPath.isEmpty()) {
            return basePath + posterPath;
        }
        return null;
    }
}
