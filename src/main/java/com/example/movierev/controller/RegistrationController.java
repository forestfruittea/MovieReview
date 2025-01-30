package com.example.movierev.controller;

import com.example.movierev.dto.UserDto;
import com.example.movierev.repository.UserRepository;
import com.example.movierev.filter.Role;

import com.example.movierev.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationPage(Model model) {
//        model.addAttribute("roles", Role.values()); // Pass roles to JSP
        return "register"; // This should map to register.jsp
    }
    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password) {
        if (userService.findByUsername(username).isPresent()) {
            return "redirect:/register?error";
        }
        String hashedPassword = passwordEncoder.encode(password);
        UserDto newUser = new UserDto();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword);
        newUser.setRole(Role.CUSTOMER);

        userService.registerUser(newUser);
        return "redirect:/login";
    }
}
