package com.example.movierev.DTOs;

import com.example.movierev.Entities.MovieEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorDto {
    private Long id;
    private String name;
    private List<MovieDto> movies;
    private String bio;
}
