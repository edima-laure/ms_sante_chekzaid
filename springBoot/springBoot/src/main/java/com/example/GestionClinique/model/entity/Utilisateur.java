package com.example.GestionClinique.model.entity;

import com.example.GestionClinique.model.InfoPersonnel;
import com.example.GestionClinique.model.entity.enumElem.ServiceMedical;
import com.example.GestionClinique.model.entity.enumElem.StatusConnect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {
        "rendezVous",
        "consultations",
        "prescriptions",
        "historiqueActions",
        "notifications"
})
public class Utilisateur extends InfoPersonnel implements UserDetails {

    // ================= AUTHENTIFICATION =================

    @Column(name = "mot_de_passe", nullable = false)
    @JsonIgnore
    @Size(min = 8, max = 255)
    private String password;

    @Column(nullable = false)
    private Boolean actif = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // ================= PROFIL =================

    @Column(name = "photo_profil")
    private String photoProfil;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "last_logout_date")
    private LocalDateTime lastLogoutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_medical")
    private ServiceMedical serviceMedical = ServiceMedical.MEDECINE_GENERALE;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_connect", nullable = false)
    private StatusConnect statusConnect = StatusConnect.DECONNECTE;

    // ================= SPRING SECURITY =================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null || role.getRoleType() == null) {
            return Collections.emptyList();
        }
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.getRoleType().name())
        );
    }

    @Override
    public String getUsername() {
        return getEmail(); // email hérité de InfoPersonnel
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.actif);
    }

    // ================= RELATIONS =================

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RendezVous> rendezVous = new ArrayList<>();

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultations = new ArrayList<>();

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriqueAction> historiqueActions = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();
}
