package com.example.movierev.DTOs;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
}
