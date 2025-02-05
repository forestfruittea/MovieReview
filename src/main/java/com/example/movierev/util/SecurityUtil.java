package com.example.movierev.util;

import com.example.movierev.wrapper.UserDetailsWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Long getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsWrapper) {
            return ((UserDetailsWrapper) authentication.getPrincipal()).getId();
        }
        return null;
    }
}