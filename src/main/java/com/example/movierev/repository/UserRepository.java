package com.example.movierev.repository;

import com.example.movierev.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    UserEntity findByName(String username);

    UserEntity update(UserEntity userEntity);

    void delete(Long userId);

    Optional<UserEntity> findById(Long userId);

    List<UserEntity> findAll();
    public boolean existsByUsername(String username);
}
