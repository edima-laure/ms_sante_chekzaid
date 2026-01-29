package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.PrescriptionRequestDto;
import com.example.GestionClinique.dto.ResponseDto.PrescriptionResponseDto;
import com.example.GestionClinique.model.entity.Consultation;
import com.example.GestionClinique.model.entity.Patient;
import com.example.GestionClinique.model.entity.Prescription;
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
public class PrescriptionMapperImpl implements PrescriptionMapper {

    @Override
    public Prescription toEntity(PrescriptionRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Prescription prescription = new Prescription();

        prescription.setTypePrescription( dto.getTypePrescription() );
        prescription.setMedicaments( dto.getMedicaments() );
        prescription.setInstructions( dto.getInstructions() );
        prescription.setDureePrescription( dto.getDureePrescription() );
        if ( dto.getQuantite() != null ) {
            prescription.setQuantite( dto.getQuantite().intValue() );
        }

        return prescription;
    }

    @Override
    public PrescriptionResponseDto toDto(Prescription entity) {
        if ( entity == null ) {
            return null;
        }

        PrescriptionResponseDto prescriptionResponseDto = new PrescriptionResponseDto();

        prescriptionResponseDto.setMedecinId( entityMedecinId( entity ) );
        prescriptionResponseDto.setPatientId( entityPatientId( entity ) );
        prescriptionResponseDto.setConsultationId( entityConsultationId( entity ) );
        prescriptionResponseDto.setId( entity.getId() );
        prescriptionResponseDto.setCreationDate( entity.getCreationDate() );
        prescriptionResponseDto.setModificationDate( entity.getModificationDate() );
        prescriptionResponseDto.setTypePrescription( entity.getTypePrescription() );
        prescriptionResponseDto.setMedicaments( entity.getMedicaments() );
        prescriptionResponseDto.setInstructions( entity.getInstructions() );
        prescriptionResponseDto.setDureePrescription( entity.getDureePrescription() );
        if ( entity.getQuantite() != null ) {
            prescriptionResponseDto.setQuantite( entity.getQuantite().longValue() );
        }

        prescriptionResponseDto.setMedecinNomComplet( entity.getMedecin() != null ? entity.getMedecin().getNom() + " " + entity.getMedecin().getPrenom() : null );
        prescriptionResponseDto.setPatientNomComplet( entity.getPatient() != null ? entity.getPatient().getNom() + " " + entity.getPatient().getPrenom() : null );
        prescriptionResponseDto.setMotifConsultation( entity.getConsultation() != null ? entity.getConsultation().getMotifs() : null );

        return prescriptionResponseDto;
    }

    @Override
    public List<PrescriptionResponseDto> toDtoList(List<Prescription> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PrescriptionResponseDto> list = new ArrayList<PrescriptionResponseDto>( entities.size() );
        for ( Prescription prescription : entities ) {
            list.add( toDto( prescription ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(PrescriptionRequestDto dto, Prescription entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTypePrescription() != null ) {
            entity.setTypePrescription( dto.getTypePrescription() );
        }
        if ( dto.getMedicaments() != null ) {
            entity.setMedicaments( dto.getMedicaments() );
        }
        if ( dto.getInstructions() != null ) {
            entity.setInstructions( dto.getInstructions() );
        }
        if ( dto.getDureePrescription() != null ) {
            entity.setDureePrescription( dto.getDureePrescription() );
        }
        if ( dto.getQuantite() != null ) {
            entity.setQuantite( dto.getQuantite().intValue() );
        }
    }

    private Long entityMedecinId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Utilisateur medecin = prescription.getMedecin();
        if ( medecin == null ) {
            return null;
        }
        Long id = medecin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPatientId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Patient patient = prescription.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityConsultationId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Consultation consultation = prescription.getConsultation();
        if ( consultation == null ) {
            return null;
        }
        Long id = consultation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
