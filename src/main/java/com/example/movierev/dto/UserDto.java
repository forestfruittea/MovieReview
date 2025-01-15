package com.example.movierev.dto;


import com.example.movierev.filter.Role;
import lombok.*;

import java.util.List;
import java.util.ResourceBundle;

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
    private Role role;
    private List<ReviewDto> reviews;

    //Uses in JSPs
    public String getFullAvatarPath() {
        String basePath = ResourceBundle.getBundle("application").getString("base.avatar.path");
        if (avatarPath != null && !avatarPath.isEmpty()) {
            return basePath + avatarPath;
        }
        return null;
    }
}
