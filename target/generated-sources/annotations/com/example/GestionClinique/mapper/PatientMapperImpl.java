package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.PatientRequestDto;
import com.example.GestionClinique.dto.ResponseDto.PatientResponseDto;
import com.example.GestionClinique.model.entity.Patient;
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
public class PatientMapperImpl implements PatientMapper {

    @Autowired
    private DossierMedicalMapper dossierMedicalMapper;

    @Override
    public Patient toEntity(PatientRequestDto patientRequestDto) {
        if ( patientRequestDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setNom( patientRequestDto.getNom() );
        patient.setPrenom( patientRequestDto.getPrenom() );
        patient.setEmail( patientRequestDto.getEmail() );
        patient.setDateNaissance( patientRequestDto.getDateNaissance() );
        patient.setTelephone( patientRequestDto.getTelephone() );
        patient.setAdresse( patientRequestDto.getAdresse() );
        patient.setGenre( patientRequestDto.getGenre() );
        patient.setDossierMedical( dossierMedicalMapper.toEntity( patientRequestDto.getDossierMedical() ) );

        return patient;
    }

    @Override
    public PatientResponseDto toDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientResponseDto patientResponseDto = new PatientResponseDto();

        patientResponseDto.setId( patient.getId() );
        patientResponseDto.setCreationDate( patient.getCreationDate() );
        patientResponseDto.setModificationDate( patient.getModificationDate() );
        patientResponseDto.setNom( patient.getNom() );
        patientResponseDto.setPrenom( patient.getPrenom() );
        patientResponseDto.setDateNaissance( patient.getDateNaissance() );
        patientResponseDto.setAge( patient.getAge() );
        patientResponseDto.setEmail( patient.getEmail() );
        patientResponseDto.setTelephone( patient.getTelephone() );
        patientResponseDto.setAdresse( patient.getAdresse() );
        patientResponseDto.setGenre( patient.getGenre() );

        return patientResponseDto;
    }

    @Override
    public List<PatientResponseDto> toDtoList(List<Patient> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientResponseDto> list = new ArrayList<PatientResponseDto>( patients.size() );
        for ( Patient patient : patients ) {
            list.add( toDto( patient ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(PatientRequestDto patientRequestDto, Patient patient) {
        if ( patientRequestDto == null ) {
            return;
        }

        if ( patientRequestDto.getNom() != null ) {
            patient.setNom( patientRequestDto.getNom() );
        }
        if ( patientRequestDto.getPrenom() != null ) {
            patient.setPrenom( patientRequestDto.getPrenom() );
        }
        if ( patientRequestDto.getDateNaissance() != null ) {
            patient.setDateNaissance( patientRequestDto.getDateNaissance() );
        }
        if ( patientRequestDto.getEmail() != null ) {
            patient.setEmail( patientRequestDto.getEmail() );
        }
        if ( patientRequestDto.getTelephone() != null ) {
            patient.setTelephone( patientRequestDto.getTelephone() );
        }
        if ( patientRequestDto.getAdresse() != null ) {
            patient.setAdresse( patientRequestDto.getAdresse() );
        }
        if ( patientRequestDto.getGenre() != null ) {
            patient.setGenre( patientRequestDto.getGenre() );
        }
    }
}
