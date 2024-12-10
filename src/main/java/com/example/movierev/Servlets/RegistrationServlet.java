package com.example.movierev.Servlets;

import com.example.movierev.Config.Role;
import com.example.movierev.DTOs.UserDto;
import com.example.movierev.Services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService;
    @Inject
    public RegistrationServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Role role = Role.CUSTOMER;

        UserDto userDto = UserDto.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        boolean success = userService.registerUser(userDto);

        if (success) {
            response.sendRedirect("/MovieRev-1.0-SNAPSHOT/login");
        } else {
            request.setAttribute("error", "Username is already taken or there was an error during registration.");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}
