package com.example.movierev.service;

import com.example.movierev.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean registerUser(UserDto userDto);
    void delete(Long userId);
    Optional<UserDto> findById(Long id);
    List<UserDto> findAll();
    List<UserDto> findAllSorted();
    Optional<UserDto> findByUsername(String username);

    //TODO UserDto update(UserDto userDto);


}
