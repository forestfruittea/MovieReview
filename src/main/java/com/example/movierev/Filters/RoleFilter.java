package com.example.movierev.Filters;

import com.example.movierev.Config.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        Role role = (Role) httpRequest.getSession().getAttribute("role");

        // Publicly accessible paths
        if (path.startsWith("/MovieRev-1.0-SNAPSHOT/login") ||
                path.startsWith("/MovieRev-1.0-SNAPSHOT/register") ||
                path.equals("/MovieRev-1.0-SNAPSHOT/")) {
            chain.doFilter(request, response);
            return;
        }

        // Role-based restrictions
        if (path.startsWith("/MovieRev-1.0-SNAPSHOT/admin") && role != Role.ADMIN) {
            httpResponse.sendRedirect("/MovieRev-1.0-SNAPSHOT");
            return;
        }

        if (path.startsWith("/MovieRev-1.0-SNAPSHOT/moderate") && !"MODERATOR".equals(role) && !"ADMIN".equals(role)) {
            httpResponse.sendRedirect("/MovieRev-1.0-SNAPSHOT");
            return;
        }

        chain.doFilter(request, response);
    }
}

