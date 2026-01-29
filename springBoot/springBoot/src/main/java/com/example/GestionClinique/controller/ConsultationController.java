package com.example.GestionClinique.controller;
import com.example.GestionClinique.dto.RequestDto.ConsultationRequestDto;
import com.example.GestionClinique.dto.RequestDto.PrescriptionRequestDto;

import com.example.GestionClinique.dto.ResponseDto.ConsultationResponseDto;
import com.example.GestionClinique.dto.ResponseDto.PrescriptionResponseDto;
import com.example.GestionClinique.mapper.ConsultationMapper;
import com.example.GestionClinique.mapper.PrescriptionMapper;
import com.example.GestionClinique.model.entity.Consultation;
import com.example.GestionClinique.model.entity.Prescription;
import com.example.GestionClinique.model.entity.Utilisateur;
import com.example.GestionClinique.service.ConsultationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.GestionClinique.configuration.utils.Constants.API_NAME;

@Tag(name = "Consultations", description = "API de gestion des consultations médicales")
@RestController
@RequestMapping(API_NAME + "/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;
    private final PrescriptionMapper prescriptionMapper;

    // ================= UTIL SÉCURITÉ =================

    private Long getAuthenticatedMedecinId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Utilisateur utilisateur)) {
            throw new IllegalStateException("Utilisateur non authentifié");
        }
        return utilisateur.getId();
    }

    // ================= CONSULTATIONS =================

    @PreAuthorize("hasRole('MEDECIN')")
    @PostMapping(
            path = "/emergency",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Créer une consultation d'urgence")
    public ResponseEntity<ConsultationResponseDto> createEmergencyConsultation(
            @Valid @RequestBody ConsultationRequestDto dto) {

        Long medecinId = getAuthenticatedMedecinId();
        Consultation consultation = consultationMapper.toEntity(dto);
        consultation.setDossierMedical(null);

        Consultation created = consultationService.createConsultation(consultation, medecinId);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultationMapper.toDto(created));
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @PostMapping(
            path = "/start/{idRendezVous}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Démarrer une consultation à partir d'un rendez-vous")
    public ResponseEntity<ConsultationResponseDto> startConsultation(
            @PathVariable Long idRendezVous,
            @Valid @RequestBody ConsultationRequestDto dto) {

        Long medecinId = getAuthenticatedMedecinId();
        Consultation consultation = consultationMapper.toEntity(dto);

        Consultation started = consultationService
                .startConsultation(idRendezVous, consultation, medecinId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consultationMapper.toDto(started));
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une consultation")
    public ResponseEntity<ConsultationResponseDto> updateConsultation(
            @PathVariable Long id,
            @Valid @RequestBody ConsultationRequestDto dto) {

        Consultation existing = consultationService.findById(id);
        consultationMapper.updateEntityFromDto(dto, existing);

        Consultation updated = consultationService.updateConsultation(id, existing);
        return ResponseEntity.ok(consultationMapper.toDto(updated));
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une consultation par ID")
    public ResponseEntity<ConsultationResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                consultationMapper.toDto(consultationService.findById(id))
        );
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @GetMapping
    @Operation(summary = "Lister toutes les consultations")
    public ResponseEntity<List<ConsultationResponseDto>> findAll() {
        List<Consultation> consultations = consultationService.findAll();
        return consultations.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(consultationMapper.toDtoList(consultations));
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une consultation")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ================= PRESCRIPTIONS =================

    @PreAuthorize("hasRole('MEDECIN')")
    @PostMapping(
            path = "/{idConsultation}/prescriptions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Ajouter une prescription à une consultation")
    public ResponseEntity<ConsultationResponseDto> addPrescription(
            @PathVariable Long idConsultation,
            @Valid @RequestBody PrescriptionRequestDto dto) {

        Prescription prescription = prescriptionMapper.toEntity(dto);
        consultationService.addPrescriptionToConsultation(idConsultation, prescription);

        Consultation updated = consultationService.findById(idConsultation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consultationMapper.toDto(updated));
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @GetMapping("/{idConsultation}/prescriptions")
    @Operation(summary = "Lister les prescriptions d'une consultation")
    public ResponseEntity<List<PrescriptionResponseDto>> findPrescriptions(
            @PathVariable Long idConsultation) {

        List<Prescription> prescriptions =
                consultationService.findPrescriptionsByConsultationId(idConsultation);

        return prescriptions.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(prescriptionMapper.toDtoList(prescriptions));
    }
}
