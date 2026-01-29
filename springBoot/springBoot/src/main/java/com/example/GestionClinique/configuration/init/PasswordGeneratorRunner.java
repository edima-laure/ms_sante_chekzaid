package com.example.GestionClinique.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGeneratorRunner implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;

    public PasswordGeneratorRunner(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        String rawPassword = "admin123";
        String encoded = passwordEncoder.encode(rawPassword);

        System.out.println("=================================");
        System.out.println("Mot de passe en clair : " + rawPassword);
        System.out.println("Mot de passe BCrypt  : " + encoded);
        System.out.println("=================================");
    }
}
