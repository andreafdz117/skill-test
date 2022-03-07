package com.abi.restproduct.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class PinManager {

    // TODO: crear las pruebas unitarias (JUnit) de esta clase y sus metodos

    private PinManager() {
    }

    public static String generateSafePin()  {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[42];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }

    public static String encodePin(String pin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pinBD = passwordEncoder.encode(pin);
        return pinBD;
    }
}
