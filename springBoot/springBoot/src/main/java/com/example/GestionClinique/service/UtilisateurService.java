package com.example.GestionClinique.service;

import com.example.GestionClinique.model.entity.RendezVous;
import com.example.GestionClinique.model.entity.Utilisateur;
import com.example.GestionClinique.model.entity.enumElem.RoleType;
import com.example.GestionClinique.model.entity.enumElem.ServiceMedical;
import com.example.GestionClinique.model.entity.enumElem.StatusConnect;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface UtilisateurService {

    // ================= CRUD UTILISATEUR =================
    Utilisateur createUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);

    Utilisateur findUtilisateurById(Long id);

    Utilisateur findUtilisateurByEmail(String email);

    List<Utilisateur> findAllUtilisateur();

    void deleteUtilisateur(Long id);

    // ================= GESTION STATUT & RÔLES =================
    Utilisateur updateUtilisateurStatus(Long id, boolean actif);

    List<Utilisateur> findUtilisateurByRole_RoleType(RoleType roleType);

    Utilisateur updateUserConnectStatus(Long utilisateurId, StatusConnect statusConnect);

    List<Utilisateur> findUsersWithStatusConnectedByOrderLastConnected();

    List<Utilisateur> findUsersWithStatusDisconnectedByOrderLastDeConnected();

    // ================= MOT DE PASSE =================
    Utilisateur updatePassword(Long utilisateurId, String newPassword, String confirmPassword);

    // ================= PHOTO DE PROFIL =================
    Utilisateur updatePhotoProfil(Long userId, MultipartFile photoProfil);

    Resource getPhotoProfil(Long userId);

    // ================= RECHERCHE =================
    List<Utilisateur> searchUsers(String searchTerm);

    List<Utilisateur> findAllByIds(List<Long> participantIds);

    // ================= RENDEZ-VOUS / MÉDECINS =================
    List<RendezVous> findConfirmedRendezVousForMedecinAndDate(
            Long medecinId,
            LocalDate date
    );

    List<Utilisateur> getAvailableMedecinsByServiceAndTime(
            ServiceMedical serviceMedical,
            LocalDate date,
            LocalTime heure
    );
}
