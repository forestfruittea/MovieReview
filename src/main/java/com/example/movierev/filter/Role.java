package com.example.movierev.filter;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER, ROLE_MODERATOR, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

