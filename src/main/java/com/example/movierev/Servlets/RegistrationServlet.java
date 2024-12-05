package com.example.movierev.Servlets;

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

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show the registration form (e.g., JSP or HTML)
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String avatarPath = request.getParameter("avatarPath"); // optional
        String role = "CUSTOMER";
        // Create DTO for user registration
        UserDto userDto = UserDto.builder()
                .username(username)
                .password(password)
                .avatarPath(avatarPath)
                .role(role)
                .build();

        // Call the service to register the user
        boolean success = userService.registerUser(userDto);

        if (success) {
            // Redirect to login page or home
            response.sendRedirect("/MovieRev-1.0-SNAPSHOT/login");
        } else {
            // Show an error message (e.g., username already taken)
            request.setAttribute("error", "Username is already taken or there was an error during registration.");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}
