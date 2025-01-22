//package com.example.movierev;
//
//import com.example.movierev.dto.UserDto;
//import com.example.movierev.service.UserService;
//import jakarta.inject.Inject;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet("/test")
//public class TestServlet extends HttpServlet {
//
//    private final DatabaseTestBean databaseTestBean;
//    @Inject
//    public TestServlet(DatabaseTestBean databaseTestBean) {
//        this.databaseTestBean = databaseTestBean;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       databaseTestBean.testConnection();
//    }
//}
