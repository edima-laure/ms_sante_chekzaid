package com.example.GestionClinique.service.authService;

import com.example.GestionClinique.model.entity.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public Long getCurrentAuthenticatedUserId() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Utilisateur utilisateur)) {
            return null; // ou throw exception selon ton besoin
        }

        return utilisateur.getId();
    }
}
