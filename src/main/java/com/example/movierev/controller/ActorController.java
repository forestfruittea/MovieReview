package com.example.movierev.controller;

import com.example.movierev.dto.ActorDto;
import com.example.movierev.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @GetMapping("/actors")
    public String listActors(Model model) {
        List<ActorDto> actors = actorService.findAll();
        model.addAttribute("actors", actors);
        return "actors";
    }
    @GetMapping("/actor")
    public String getActorDetails(@RequestParam(name = "id") Long id, Model model) {
        Optional<ActorDto> actor = actorService.findById(id);
        if (actor.isPresent()) {
            model.addAttribute("actor", actor.get());
            return "actor-details";
        } else {
            return "redirect:/actors";
        }
    }
}
