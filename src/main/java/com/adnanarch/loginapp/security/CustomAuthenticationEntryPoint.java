package com.adnanarch.loginapp.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Author: Adnan Rafique
 * Date: 4/20/2024
 * Time: 11:52 AM
 * Version: 1.0
 */

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final String loginUrl;

    public CustomAuthenticationEntryPoint(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Redirect to your custom login page
        response.sendRedirect(loginUrl);
    }
}

