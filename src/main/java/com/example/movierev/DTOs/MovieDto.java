package com.example.movierev.DTOs;




import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.DirectorEntity;
import com.example.movierev.Entities.GenreEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String country;
    private DirectorDto director;  // Just the director's name as a string
    private List<ActorDto> actors;  // List of actor names as strings
    private List<GenreDto> genres;
    private LocalDate releaseDate;
    private String posterPath;
    private Long length;
    private Long budget;
    private Long boxOffice;

}
