package com.example.movierev.DTOs;

import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {

    private Long id;
    private UserEntity user;
    private MovieEntity movie;
    private int rating;
}