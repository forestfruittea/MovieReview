package com.example.movierev.mapper.impl;

import com.example.movierev.dto.UserDto;
import com.example.movierev.entity.UserEntity;
import com.example.movierev.mapper.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class UserMapper implements Mapper<UserEntity, UserDto> {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public UserEntity toEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
}
