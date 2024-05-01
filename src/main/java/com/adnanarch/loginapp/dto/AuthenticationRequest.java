package com.adnanarch.loginapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Adnan Rafique
 * Date: 4/20/2024
 * Time: 9:52 PM
 * Version: 1.0
 */
@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
}
