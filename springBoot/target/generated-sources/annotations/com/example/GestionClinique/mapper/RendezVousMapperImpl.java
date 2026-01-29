package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.RendezVousRequestDto;
import com.example.GestionClinique.dto.ResponseDto.RendezVousResponseDto;
import com.example.GestionClinique.model.entity.Patient;
import com.example.GestionClinique.model.entity.RendezVous;
import com.example.GestionClinique.model.entity.Salle;
import com.example.GestionClinique.model.entity.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class RendezVousMapperImpl extends RendezVousMapper {

    @Override
    public RendezVous toEntity(RendezVousRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        RendezVous rendezVous = new RendezVous();

        rendezVous.setPatient( mapPatientIdToPatient( dto.getPatientId() ) );
        rendezVous.setMedecin( mapMedecinIdToMedecin( dto.getMedecinId() ) );
        rendezVous.setHeure( dto.getHeure() );
        rendezVous.setJour( dto.getJour() );
        rendezVous.setNotes( dto.getNotes() );
        rendezVous.setServiceMedical( dto.getServiceMedical() );

        return rendezVous;
    }

    @Override
    public RendezVousResponseDto toDto(RendezVous entity) {
        if ( entity == null ) {
            return null;
        }

        RendezVousResponseDto rendezVousResponseDto = new RendezVousResponseDto();

        rendezVousResponseDto.setPatientId( entityPatientId( entity ) );
        rendezVousResponseDto.setMedecinId( entityMedecinId( entity ) );
        rendezVousResponseDto.setSalleId( entitySalleId( entity ) );
        rendezVousResponseDto.setServiceMedical( entity.getServiceMedical() );
        rendezVousResponseDto.setNomSalle( entitySalleNumeroSalle( entity ) );
        rendezVousResponseDto.setId( entity.getId() );
        rendezVousResponseDto.setCreationDate( entity.getCreationDate() );
        rendezVousResponseDto.setModificationDate( entity.getModificationDate() );
        rendezVousResponseDto.setJour( entity.getJour() );
        rendezVousResponseDto.setHeure( entity.getHeure() );
        rendezVousResponseDto.setStatut( entity.getStatut() );
        rendezVousResponseDto.setNotes( entity.getNotes() );

        rendezVousResponseDto.setPatientNomComplet( entity.getPatient() != null ? entity.getPatient().getPrenom() + " " + entity.getPatient().getNom() : null );
        rendezVousResponseDto.setMedecinNomComplet( entity.getMedecin() != null ? entity.getMedecin().getPrenom() + " " + entity.getMedecin().getNom() : null );
        rendezVousResponseDto.setFactureId( entity.getFacture() != null ? entity.getFacture().getId() : null );

        return rendezVousResponseDto;
    }

    @Override
    public List<RendezVousResponseDto> toDtoList(List<RendezVous> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RendezVousResponseDto> list = new ArrayList<RendezVousResponseDto>( entities.size() );
        for ( RendezVous rendezVous : entities ) {
            list.add( toDto( rendezVous ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(RendezVousRequestDto dto, RendezVous entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getJour() != null ) {
            entity.setJour( dto.getJour() );
        }
        if ( dto.getHeure() != null ) {
            entity.setHeure( dto.getHeure() );
        }
        if ( dto.getNotes() != null ) {
            entity.setNotes( dto.getNotes() );
        }
        if ( dto.getServiceMedical() != null ) {
            entity.setServiceMedical( dto.getServiceMedical() );
        }
    }

    private Long entityPatientId(RendezVous rendezVous) {
        if ( rendezVous == null ) {
            return null;
        }
        Patient patient = rendezVous.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityMedecinId(RendezVous rendezVous) {
        if ( rendezVous == null ) {
            return null;
        }
        Utilisateur medecin = rendezVous.getMedecin();
        if ( medecin == null ) {
            return null;
        }
        Long id = medecin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entitySalleId(RendezVous rendezVous) {
        if ( rendezVous == null ) {
            return null;
        }
        Salle salle = rendezVous.getSalle();
        if ( salle == null ) {
            return null;
        }
        Long id = salle.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entitySalleNumeroSalle(RendezVous rendezVous) {
        if ( rendezVous == null ) {
            return null;
        }
        Salle salle = rendezVous.getSalle();
        if ( salle == null ) {
            return null;
        }
        String numeroSalle = salle.getNumeroSalle();
        if ( numeroSalle == null ) {
            return null;
        }
        return numeroSalle;
    }
}
