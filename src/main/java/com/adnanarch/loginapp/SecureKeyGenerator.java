package com.adnanarch.loginapp;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Author: Adnan Rafique
 * Date: 4/20/2024
 * Time: 11:51 PM
 * Version: 1.0
 */

public class SecureKeyGenerator {
    public static String generateSecureKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256, new SecureRandom()); // Initialize with 256 bits and a secure random instance
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Failed to generate a secure key: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String secureKey = generateSecureKey();
        if (secureKey != null) {
            System.out.println("Generated secure key: " + secureKey);
        }
    }
}
