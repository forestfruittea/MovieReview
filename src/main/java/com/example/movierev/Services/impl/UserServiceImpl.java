package com.example.movierev.Services.impl;

import com.example.movierev.Config.Role;
import com.example.movierev.DTOs.ActorDto;
import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Entities.ActorEntity;
import com.example.movierev.Entities.UserEntity;
import com.example.movierev.Mappers.impl.UserMapper;
import com.example.movierev.Repositories.UserRepository;
import com.example.movierev.Services.UserService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return false;
        }
        String hashedPassword = hashPassword(userDto.getPassword());

        UserEntity newUser = userMapper.toEntity(userDto);
        newUser.setAvatarPath(ResourceBundle.getBundle("application").getString("base.avatar"));
        newUser.setPassword(hashedPassword);


        try {
            userRepository.save(newUser);
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Long authenticate(String username, String password) {
        UserEntity user = userRepository.findByName(username);

        if (user != null && verifyPassword(password, user.getPassword())) {
            return user.getId();
        }
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<UserDto> findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::toDto);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<UserDto> findAll() {
        return null;
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<UserDto> findAllSorted() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .sorted((m1, m2) -> {
                    String title1 = m1.getUsername().toLowerCase();
                    String title2 = m2.getUsername().toLowerCase();
                    return title1.compareTo(title2);
                })
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private boolean verifyPassword(String plaintextPassword, String hashedPassword) {
        return BCrypt.checkpw(plaintextPassword, hashedPassword);
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Long getLoggedInUserId(HttpServletRequest req) {
        // Retrieve the session
        HttpSession session = req.getSession(false); // Use false to avoid creating a new session if none exists

        if (session != null) {
            // Get the user ID from the session attributes
            Object userId = session.getAttribute("userId");

            if (userId != null) {
                return (Long) userId; // Return the user ID if present
            }
        }

        return null; // Return null if no user is logged in
    }
}
