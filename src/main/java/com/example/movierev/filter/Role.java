package com.example.movierev.filter;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER, MODERATOR, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

