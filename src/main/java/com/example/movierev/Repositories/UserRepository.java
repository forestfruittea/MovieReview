package com.example.movierev.Repositories;

import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity createUser(UserEntity userEntity);
    UserEntity findUserByName(String username);

    UserEntity updateUser(UserEntity userEntity);

    void deleteUser(Long userId);

    Optional<UserEntity> findUserById(Long userId);

    List<UserEntity> findAllUsers();
    public boolean existsByUsername(String username);
}
