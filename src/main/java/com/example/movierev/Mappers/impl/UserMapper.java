package com.example.movierev.Mappers.impl;

import com.example.movierev.DTOs.MovieDto;
import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Entities.UserEntity;
import com.example.movierev.Mappers.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.Map;
@ApplicationScoped
public class UserMapper implements Mapper<UserEntity, UserDto> {
    @Inject
    private ModelMapper modelMapper;
    /*@Inject
    public MovieMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }*/
    @Override
    public UserEntity toEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
}
