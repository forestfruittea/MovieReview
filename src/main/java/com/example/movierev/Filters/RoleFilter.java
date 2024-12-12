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

        if (role == null) {
            httpResponse.sendRedirect("/MovieRev-1.0-SNAPSHOT");
            return;
        }

        if (!isAuthorized(role, path)) {
            httpResponse.sendRedirect("/MovieRev-1.0-SNAPSHOT");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthorized(Role role, String path) {
        switch (role) {
            case CUSTOMER:
                return !path.startsWith("/MovieRev-1.0-SNAPSHOT/admin");

            case MODERATOR:
                if (path.startsWith("/MovieRev-1.0-SNAPSHOT/admin/tool/movies") ||
                        path.startsWith("/MovieRev-1.0-SNAPSHOT/admin/tool/actors") ||
                        path.startsWith("/MovieRev-1.0-SNAPSHOT/admin/tool/directors") ||
                        path.startsWith("/MovieRev-1.0-SNAPSHOT/admin/tool/genres")) {
                    return false;
                }
                return true; // Allow everything else

            case ADMIN:
                // ADMIN can access everything
                return true;

            default:
                return false; // Block access for undefined roles
        }
    }
}
