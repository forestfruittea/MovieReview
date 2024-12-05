package com.example.movierev.Services;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.DTOs.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean registerUser(UserDto userDto);
    Long authenticate(String username, String password);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long userId);
    Optional<UserDto> getUserById(Long id);
    List<UserDto> getAllUsers();

}
