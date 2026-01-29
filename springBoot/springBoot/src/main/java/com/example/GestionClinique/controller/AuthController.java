package com.example.GestionClinique.controller;

import com.example.GestionClinique.configuration.security.jwtConfig.JwtUtil;
import com.example.GestionClinique.dto.auth.LoginRequest;
import com.example.GestionClinique.dto.auth.LoginResponse;
import com.example.GestionClinique.model.entity.Utilisateur;
import com.example.GestionClinique.service.HistoriqueActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.GestionClinique.configuration.utils.Constants.API_NAME;

@Tag(name = "AUTHENTIFICATION", description = "API de connexion")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final HistoriqueActionService historiqueActionService;

    @PostMapping(
            path = API_NAME + "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Connexion utilisateur")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();

        String token = jwtUtil.generateToken(utilisateur);

        historiqueActionService.enregistrerAction(
                "Connexion utilisateur",
                utilisateur.getId()
        );

        return ResponseEntity.ok(
                new LoginResponse(
                        utilisateur.getId(),
                        token,
                        utilisateur.getUsername(),
                        utilisateur.getPhotoProfil(),
                        utilisateur.getAuthorities()
                )
        );
    }
}
