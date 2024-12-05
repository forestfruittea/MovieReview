package com.example.movierev.DTOs;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserDto user; // Replacing username and userAvatar with UserDto
    private Long movieId;
}
