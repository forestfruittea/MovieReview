package com.example.movierev.DTOs;

import jakarta.inject.Named;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String country;
    private String starringRole;
    private String director;
    private LocalDate releaseDate;
    private String genre;
    private String posterPath;
    private Long length;
    private Long budget;
    private Long boxOffice;
}
