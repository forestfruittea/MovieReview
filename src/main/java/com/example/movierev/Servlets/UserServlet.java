package com.example.movierev.Servlets;

import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Services.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userRequest = objectMapper.readValue(req.getReader(), UserDto.class);

        String username = userRequest.getUsername();
        String password = userRequest.getPassword();
        if (username != null && password != null) {
            userService.createUser(username, password);
            resp.getWriter().write("User created successfully!");
        } else {
            resp.getWriter().write("Missing required parameters!");
        }

    }
}
