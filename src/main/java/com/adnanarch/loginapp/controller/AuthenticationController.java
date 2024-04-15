package com.adnanarch.loginapp.controller;

import com.adnanarch.loginapp.dto.RegistrationRequest;
import com.adnanarch.loginapp.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Author: Adnan Rafique
 * Date: 4/9/2024
 * Time: 11:06 PM
 * Version: 1.0
 */

@RestController
@RequiredArgsConstructor
//@RequestMapping("auth")
@Tag(name = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/api/v1/auth/register")
    @ResponseStatus(CREATED)
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
        authenticationService.register(registrationRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/test")
    public String test() {
        return "Test";
    }
}
