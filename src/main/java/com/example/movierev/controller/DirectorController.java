package com.example.movierev.controller;

import com.example.movierev.dto.DirectorDto;
import com.example.movierev.service.DirectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }



    // 5. Display Director Details
    @GetMapping("/director")
    public String showDirectorDetails(@RequestParam(name = "id") Long id, Model model) {
        Optional<DirectorDto> directorDto = directorService.findById(id);

        if (directorDto.isPresent()) {
            model.addAttribute("director", directorDto.get());
            return "director-details"; // Corresponds to /WEB-INF/director-details.jsp
        } else {
            model.addAttribute("error", "Director not found.");
            return "error"; // Generic error page
        }
    }

    // 6. List All Directors
    @GetMapping("/directors")
    public String listDirectors(Model model) {
        List<DirectorDto> directors = directorService.findAll();
        model.addAttribute("directors", directors);
        return "directors"; // Corresponds to /WEB-INF/directors.jsp
    }
}
