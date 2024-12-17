package com.example.movierev.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorDto {
    private Long id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;
    private List<MovieDto> movies;
    @Size(max = 1500, message = "Bio must be at most 1500 characters")
    private String bio;
    private LocalDate dateOfBirth;
    private String photoPath;
    public String getFullPhotoPath() {
        String basePath = ResourceBundle.getBundle("application").getString("base.photo.path");
        if (photoPath != null && !photoPath.isEmpty()) {
            return basePath + photoPath;
        }
        return null;
    }
}
