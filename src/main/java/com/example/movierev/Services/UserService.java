package com.example.movierev.Services;

import com.example.movierev.DTOs.UserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean registerUser(UserDto userDto);
    Long authenticate(String username, String password);
    UserDto update(UserDto userDto);
    void delete(Long userId);
    Optional<UserDto> findById(Long id);
    List<UserDto> findAll();
    Long getLoggedInUserId(HttpServletRequest req);

}
