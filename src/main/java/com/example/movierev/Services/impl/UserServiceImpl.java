package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Entities.UserEntity;
import com.example.movierev.Mappers.Mapper;
import com.example.movierev.Mappers.impl.UserMapper;
import com.example.movierev.Repositories.UserRepository;
import com.example.movierev.Services.UserService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@Stateless
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Inject
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        // Check if the username already exists
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return false; // Username already taken
        }

        // Hash the password (use bcrypt for password hashing)
        String hashedPassword = hashPassword(userDto.getPassword());

        // Create a new user entity
        UserEntity newUser = userMapper.toEntity(userDto);
        newUser.setPassword(hashedPassword);

        if (newUser.getRole() == null || newUser.getRole().isEmpty()) {
            newUser.setRole("CUSTOMER");
        }

        try {
            // Save the new user to the database
            userRepository.createUser(newUser);
            return true; // User registration successful
        } catch (PersistenceException e) {
            // Handle database error (e.g., unique constraint violation)
            return false; // Registration failed
        }
    }

    public Long authenticate(String username, String password) {
        // Find the user by username
        UserEntity user = userRepository.findUserByName(username);

        if (user != null && verifyPassword(password, user.getPassword())) {
            // Password matches, return user ID
            return user.getId();
        }

        // Authentication failed
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findUserById(id);
        return userEntity.map(userMapper::toDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }
    private String hashPassword(String password) {
        // Use bcrypt to hash the password with a work factor of 12 (you can adjust this as needed)
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 is the strength factor (higher = slower hashing)
    }
    private boolean verifyPassword(String plaintextPassword, String hashedPassword) {
        return BCrypt.checkpw(plaintextPassword, hashedPassword);
    }
}
