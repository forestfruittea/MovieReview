package com.example.movierev.service;

import com.example.movierev.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean registerUser(UserDto userDto);
    Long authenticate(String username, String password);
    void delete(Long userId);
    Optional<UserDto> findById(Long id);
    List<UserDto> findAll();
    List<UserDto> findAllSorted();
    Long getLoggedInUserId(HttpServletRequest req);

    //TODO UserDto update(UserDto userDto);


}
