package com.adnanarch.loginapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Adnan Rafique
 * Date: 4/20/2024
 * Time: 9:56 PM
 * Version: 1.0
 */

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}
