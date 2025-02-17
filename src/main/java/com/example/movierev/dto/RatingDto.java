package com.example.movierev.dto;

import com.example.movierev.entity.GenreEntity;
import com.example.movierev.entity.RatingEntity;
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
    public static RatingDto of(RatingEntity rating) {
        return RatingDto.builder()
                .id(rating.getId())
                .user(UserDto.of(rating.getUser()))
                .movie(MovieDto.ofReview(rating.getMovie()))
                // Map only movies without their actors to avoid lazy initialization issues
                .rating(rating.getRating())
                .build();
    }
}
