package com.example.movierev.Config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class MapperConfig {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
