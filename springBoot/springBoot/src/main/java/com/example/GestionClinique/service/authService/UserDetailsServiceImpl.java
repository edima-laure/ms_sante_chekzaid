package com.example.GestionClinique.service.authService;

import com.example.GestionClinique.model.entity.Utilisateur;
import com.example.GestionClinique.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Utilisateur non trouvé avec l'email : " + email
                        )
                );

        if (!Boolean.TRUE.equals(utilisateur.getActif())) {
            throw new UsernameNotFoundException(
                    "Utilisateur désactivé : " + email
            );
        }

        // ✅ RETOUR SIMPLE — aucune logique métier ici
        return utilisateur; // Utilisateur implémente UserDetails
    }
}
