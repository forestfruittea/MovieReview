package com.example.movierev.Services.impl;

import com.example.movierev.Entities.UserEntity;
import com.example.movierev.Repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository userRepository;
    public void createUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password); // Remember to hash passwords in real apps
        userRepository.createUser(user);
    }
}
