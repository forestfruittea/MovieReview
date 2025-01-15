package com.example.movierev.filter;

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

        String contextPath = httpRequest.getContextPath();
        String path = httpRequest.getRequestURI();
        Role role = (Role) httpRequest.getSession().getAttribute("role");

        // Publicly accessible paths
        if (path.startsWith(contextPath+"/login") ||
                path.startsWith(contextPath+"/register") ||
                path.equals(contextPath+"/")) {
            if (role == null) {
                chain.doFilter(request, response);
                return; // Allow access for non-logged-in users to login/register pages
            } else {
                // Redirect to the homepage or dashboard if already logged in
                httpResponse.sendRedirect(contextPath+"/");
                return;
            }
        }

        // Allow all other pages (excluding account-related pages) for non-logged-in users
        if (role == null && !path.startsWith(contextPath+"/account") || !path.startsWith(contextPath+"/admin")) {
            chain.doFilter(request, response);
            return;
        }

        if (role == null) {
            httpResponse.sendRedirect(contextPath);
            return;
        }

        if (!isAuthorized(role, path, contextPath)) {
            httpResponse.sendRedirect(contextPath);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isAuthorized(Role role, String path, String contextPath) {
        switch (role) {
            case CUSTOMER:
                return !path.startsWith(contextPath+"/admin");

            case MODERATOR:
                if (path.startsWith(contextPath+"/admin/tool/movies") ||
                        path.startsWith(contextPath+"/admin/tool/actors") ||
                        path.startsWith(contextPath+"/admin/tool/directors") ||
                        path.startsWith(contextPath+"/admin/tool/genres")) {
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
