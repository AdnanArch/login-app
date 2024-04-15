package com.adnanarch.loginapp.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Author: Adnan Rafique
 * Date: 4/9/2024
 * Time: 11:12 PM
 * Version: 1.0
 */

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotEmpty(message = "CNIC is required")
    @NotBlank(message = "CNIC is required")
    @Size(min = 13, max = 13, message = "CNIC must be 13 characters")
//    @Pattern()
    private String cnic;
}
