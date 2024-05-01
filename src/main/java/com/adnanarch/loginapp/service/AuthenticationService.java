package com.adnanarch.loginapp.service;

import com.adnanarch.loginapp.dto.AuthenticationRequest;
import com.adnanarch.loginapp.dto.AuthenticationResponse;
import com.adnanarch.loginapp.dto.UserDto;
import com.adnanarch.loginapp.enumeration.EmailTemplateName;
import com.adnanarch.loginapp.model.Token;
import com.adnanarch.loginapp.model.User;
import com.adnanarch.loginapp.repository.RoleRepository;
import com.adnanarch.loginapp.repository.TokenRepository;
import com.adnanarch.loginapp.repository.UserRepository;
import com.adnanarch.loginapp.security.JwtService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Adnan Rafique
 * Date: 4/9/2024
 * Time: 11:08 PM
 * Version: 1.0
 */

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activationUrl}")
    private String activationUrl;

    public void register(UserDto registrationRequest) throws MessagingException {
        var userRole = roleRepository.findByName("USER").orElseThrow(() -> new IllegalStateException("Error: Role is not found."));
        var user = User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .accountLocked(false)
                .cnicNo(registrationRequest.getCnic())
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        // send email
        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account Activation");
    }

    private String generateAndSaveActivationToken(User user) {
        // generate token
        String generatedToken = generateActivationCode();
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode() {
        String objectChars = "0123456789";
        StringBuilder code = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            code.append(objectChars.charAt(random.nextInt(objectChars.length())));
        }
        return code.toString();
    }

    @Transactional
    public void verifyToken(String token) throws MessagingException {
        var tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));
        if (tokenEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            sendValidationEmail(tokenEntity.getUser());
            throw new IllegalStateException("Token has expired. New token has been sent to your email.");
        }
        tokenEntity.getUser().setEnabled(true);
        userRepository.save(tokenEntity.getUser());
        tokenRepository.delete(tokenEntity);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = (User) auth.getPrincipal();
        claims.put("fullName", user.getFullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
