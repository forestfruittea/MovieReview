package com.example.movierev.service;

import com.example.movierev.dto.UserDto;
import com.example.movierev.filter.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean registerUser(UserDto userDto);
    void delete(Long userId);
    Optional<UserDto> findById(Long id);
//TODO    List<UserDto> findAll();
    List<UserDto> findAllSorted();
    Optional<UserDto> findByUsername(String username);
    Long getLoggedInUserId(HttpServletRequest req);

    //TODO UserDto update(UserDto userDto);


}
