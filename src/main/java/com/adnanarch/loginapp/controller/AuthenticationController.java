package com.adnanarch.loginapp.controller;

import com.adnanarch.loginapp.dto.AuthenticationRequest;
import com.adnanarch.loginapp.dto.AuthenticationResponse;
import com.adnanarch.loginapp.dto.UserDto;
import com.adnanarch.loginapp.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.ACCEPTED;
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

    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    public ResponseEntity<String> register(@RequestBody @Valid UserDto registrationRequest) throws MessagingException {
        authenticationService.register(registrationRequest);
        return new ResponseEntity<>("User registered successfully", CREATED);
    }

    @PostMapping("/signup/verification")
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<String> verify(@RequestBody Map<String, String> tokenData) throws MessagingException {
        String token = tokenData.get("token");
        System.out.println("Token: " + token);
        authenticationService.verifyToken(token);
        return new ResponseEntity<>("Account verified successfully", ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        System.out.println("Request: " + request.getEmail());
        System.out.println("Request: " + request.getPassword());

        return ResponseEntity.ok().body(authenticationService.authenticate(request));
    }
}
