package com.example.movierev.service.impl;

import com.example.movierev.filter.Role;
import com.example.movierev.dto.UserDto;
import com.example.movierev.entity.UserEntity;
import com.example.movierev.mapper.impl.UserMapper;
import com.example.movierev.repository.UserRepository;
import com.example.movierev.service.UserService;
import com.example.movierev.wrapper.UserDetailsWrapper;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return false;
        }

        setAvatarForRole(userDto.getRole(), userDto);
        UserEntity newUser = userMapper.toEntity(userDto);
        try {
            userRepository.save(newUser);
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }
    public void setAvatarForRole(Role role, UserDto userDto) {
        String avatarPath;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

        switch (role) {
            case CUSTOMER:
                avatarPath = resourceBundle.getString("base.avatar");
                break;
            case MODERATOR:
                avatarPath = resourceBundle.getString("moderator.avatar");
                break;
            case ADMIN:
                avatarPath = resourceBundle.getString("admin.avatar");
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }

        userDto.setAvatarPath(avatarPath);
    }
    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(UserDto::of);
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }
    @Override
    public List<UserDto> findAllSorted() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .sorted((m1, m2) -> {
                    String title1 = m1.getUsername().toLowerCase();
                    String title2 = m2.getUsername().toLowerCase();
                    return title1.compareTo(title2);
                })
                .map(UserDto::of)
                .collect(Collectors.toList());

    }
    @Override
    public Optional<UserDto> findByUsername(String username){
        return userRepository.findByUsername(username).map(UserDto::of);
    }
}
