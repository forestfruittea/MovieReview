package com.example.movierev.Servlets;

import com.example.movierev.DTOs.ReviewDto;
import com.example.movierev.Services.ReviewService;
import com.example.movierev.Services.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/account/reviews")
public class AccountReviewsServlet extends HttpServlet {
    private final ReviewService reviewService;
    private final UserService userService;
    @Inject
    public AccountReviewsServlet(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = userService.getLoggedInUserId(req); // Placeholder logic for now
        List<ReviewDto> reviews = reviewService.findAllForUser(userId);

        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/WEB-INF/account-reviews.jsp").forward(req, resp);
    }
}
