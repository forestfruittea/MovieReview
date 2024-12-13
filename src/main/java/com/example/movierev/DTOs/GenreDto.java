package com.example.movierev.DTOs;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDto {
    private Long id;
    @NotNull(message = "Genre name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    private List<MovieDto> movies;
    private String description;
    private String imagePath;

    public String getFullImagePath() {
        String basePath = ResourceBundle.getBundle("application").getString("base.genreImage.path");
        if (imagePath != null && !imagePath.isEmpty()) {
            return basePath + imagePath;
        }
        return null;
    }
}
