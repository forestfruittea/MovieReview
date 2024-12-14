package com.example.movierev.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {

    private Long id;
    private UserDto user;
    private MovieDto movie;
    private int rating;
}
