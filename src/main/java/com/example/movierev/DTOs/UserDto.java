package com.example.movierev.DTOs;


import com.example.movierev.Entities.ReviewEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String avatarPath;
    private String role;
    private List<ReviewEntity> reviews;
}
