package com.example.movierev.Services.impl;

import com.example.movierev.DTOs.UserDto;
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
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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

        if (newUser.getRole() == null || newUser.getRole().isEmpty()) {
            newUser.setRole("CUSTOMER");
        }

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
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private boolean verifyPassword(String plaintextPassword, String hashedPassword) {
        return BCrypt.checkpw(plaintextPassword, hashedPassword);
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Long getLoggedInUserId(HttpServletRequest req) {
        //Mock
        Long user = 4L;
        return user;
    }
}
