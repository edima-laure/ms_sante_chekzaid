package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.ConsultationRequestDto;
import com.example.GestionClinique.dto.RequestDto.PrescriptionRequestDto;
import com.example.GestionClinique.dto.ResponseDto.ConsultationResponseDto;
import com.example.GestionClinique.model.entity.Consultation;
import com.example.GestionClinique.model.entity.Prescription;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ConsultationMapperImpl implements ConsultationMapper {

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Override
    public Consultation toEntity(ConsultationRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Consultation consultation = new Consultation();

        consultation.setPrescriptions( prescriptionRequestDtoListToPrescriptionList( dto.getPrescriptions() ) );
        consultation.setPoids( dto.getPoids() );
        consultation.setTaille( dto.getTaille() );
        consultation.setTemperature( dto.getTemperature() );
        consultation.setTensionArterielle( dto.getTensionArterielle() );
        consultation.setMotifs( dto.getMotifs() );
        consultation.setCompteRendu( dto.getCompteRendu() );
        consultation.setDiagnostic( dto.getDiagnostic() );

        return consultation;
    }

    @Override
    public ConsultationResponseDto toDto(Consultation entity) {
        if ( entity == null ) {
            return null;
        }

        ConsultationResponseDto consultationResponseDto = new ConsultationResponseDto();

        consultationResponseDto.setPrescriptions( prescriptionMapper.toDtoList( entity.getPrescriptions() ) );
        consultationResponseDto.setId( entity.getId() );
        consultationResponseDto.setCreationDate( entity.getCreationDate() );
        consultationResponseDto.setModificationDate( entity.getModificationDate() );
        consultationResponseDto.setPoids( entity.getPoids() );
        consultationResponseDto.setTaille( entity.getTaille() );
        consultationResponseDto.setTensionArterielle( entity.getTensionArterielle() );
        consultationResponseDto.setTemperature( entity.getTemperature() );
        consultationResponseDto.setMotifs( entity.getMotifs() );
        consultationResponseDto.setDiagnostic( entity.getDiagnostic() );
        consultationResponseDto.setCompteRendu( entity.getCompteRendu() );

        consultationResponseDto.setMedecinNomComplet( entity.getMedecin() != null ? entity.getMedecin().getNom() + " " + entity.getMedecin().getPrenom() : null );
        consultationResponseDto.setPatientNomComplet( entity.getDossierMedical() != null && entity.getDossierMedical().getPatient() != null ? entity.getDossierMedical().getPatient().getNom() + " " + entity.getDossierMedical().getPatient().getPrenom() : null );
        consultationResponseDto.setServiceMedecin( entity.getMedecin() != null && entity.getMedecin().getServiceMedical() != null ? entity.getMedecin().getServiceMedical().name() : null );

        return consultationResponseDto;
    }

    @Override
    public List<ConsultationResponseDto> toDtoList(List<Consultation> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ConsultationResponseDto> list = new ArrayList<ConsultationResponseDto>( entities.size() );
        for ( Consultation consultation : entities ) {
            list.add( toDto( consultation ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(ConsultationRequestDto dto, Consultation entity) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getPrescriptions() != null ) {
            List<Prescription> list = prescriptionRequestDtoListToPrescriptionList( dto.getPrescriptions() );
            if ( list != null ) {
                entity.getPrescriptions().clear();
                entity.getPrescriptions().addAll( list );
            }
        }
        else {
            List<Prescription> list = prescriptionRequestDtoListToPrescriptionList( dto.getPrescriptions() );
            if ( list != null ) {
                entity.setPrescriptions( list );
            }
        }
        if ( dto.getPoids() != null ) {
            entity.setPoids( dto.getPoids() );
        }
        if ( dto.getTaille() != null ) {
            entity.setTaille( dto.getTaille() );
        }
        if ( dto.getTemperature() != null ) {
            entity.setTemperature( dto.getTemperature() );
        }
        if ( dto.getTensionArterielle() != null ) {
            entity.setTensionArterielle( dto.getTensionArterielle() );
        }
        if ( dto.getMotifs() != null ) {
            entity.setMotifs( dto.getMotifs() );
        }
        if ( dto.getCompteRendu() != null ) {
            entity.setCompteRendu( dto.getCompteRendu() );
        }
        if ( dto.getDiagnostic() != null ) {
            entity.setDiagnostic( dto.getDiagnostic() );
        }
    }

    protected List<Prescription> prescriptionRequestDtoListToPrescriptionList(List<PrescriptionRequestDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Prescription> list1 = new ArrayList<Prescription>( list.size() );
        for ( PrescriptionRequestDto prescriptionRequestDto : list ) {
            list1.add( prescriptionMapper.toEntity( prescriptionRequestDto ) );
        }

        return list1;
    }
}
