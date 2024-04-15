package com.adnanarch.loginapp.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Author: Adnan Rafique
 * Date: 4/12/2024
 * Time: 4:53 AM
 * Version: 1.0
 */

public class ImageToBase64 {
    public static void main(String[] args) throws Exception {
        // Read image file into byte array
        byte[] imageData = Files.readAllBytes(Paths.get("src/main/resources/images/image-2.png"));

        // Encode byte array to Base64 string
        String base64EncodedImage = Base64.getEncoder().encodeToString(imageData);

        System.out.println("Base64 Encoded Image:");
        System.out.println(base64EncodedImage);
    }
}

