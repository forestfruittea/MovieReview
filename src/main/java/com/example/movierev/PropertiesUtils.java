package com.example.movierev;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

import java.util.ResourceBundle;

@ApplicationScoped
@Getter
public class PropertiesUtils {
    private static final String BASE_POSTER_PATH = ResourceBundle.getBundle("application").getString("base.poster.path");
    public String getBasePosterPath(){
        return BASE_POSTER_PATH;
    }
}
