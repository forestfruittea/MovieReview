package com.example.movierev.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
@Slf4j
@WebFilter("/*")
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String lang = httpRequest.getParameter("lang");
        if (lang != null) {
            session.setAttribute("lang", lang);
        }

        String language = (String) session.getAttribute("lang");
        if (language == null) {
            language = "en";
        }
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        chain.doFilter(request, response);
    }
}
