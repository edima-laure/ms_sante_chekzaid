package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.DossierMedicalRequestDto;
import com.example.GestionClinique.dto.ResponseDto.ConsultationResponseDto;
import com.example.GestionClinique.dto.ResponseDto.DossierMedicalResponseDto;
import com.example.GestionClinique.dto.ResponseDto.PatientResponseDto;
import com.example.GestionClinique.dto.ResponseDto.PrescriptionResponseDto;
import com.example.GestionClinique.model.entity.Consultation;
import com.example.GestionClinique.model.entity.DossierMedical;
import com.example.GestionClinique.model.entity.Patient;
import com.example.GestionClinique.model.entity.Prescription;
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
public class DossierMedicalMapperImpl implements DossierMedicalMapper {

    @Override
    public DossierMedical toEntity(DossierMedicalRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        DossierMedical dossierMedical = new DossierMedical();

        dossierMedical.setGroupeSanguin( dto.getGroupeSanguin() );
        dossierMedical.setAntecedentsMedicaux( dto.getAntecedentsMedicaux() );
        dossierMedical.setAllergies( dto.getAllergies() );
        dossierMedical.setDernierTraitement( dto.getDernierTraitement() );
        dossierMedical.setObservations( dto.getObservations() );

        return dossierMedical;
    }

    @Override
    public DossierMedicalResponseDto toDto(DossierMedical entity) {
        if ( entity == null ) {
            return null;
        }

        DossierMedicalResponseDto dossierMedicalResponseDto = new DossierMedicalResponseDto();

        dossierMedicalResponseDto.setPatient( patientToPatientResponseDto( entity.getPatient() ) );
        dossierMedicalResponseDto.setId( entity.getId() );
        dossierMedicalResponseDto.setCreationDate( entity.getCreationDate() );
        dossierMedicalResponseDto.setModificationDate( entity.getModificationDate() );
        dossierMedicalResponseDto.setGroupeSanguin( entity.getGroupeSanguin() );
        dossierMedicalResponseDto.setAntecedentsMedicaux( entity.getAntecedentsMedicaux() );
        dossierMedicalResponseDto.setAllergies( entity.getAllergies() );
        dossierMedicalResponseDto.setDernierTraitement( entity.getDernierTraitement() );
        dossierMedicalResponseDto.setObservations( entity.getObservations() );
        dossierMedicalResponseDto.setConsultations( consultationListToConsultationResponseDtoList( entity.getConsultations() ) );

        return dossierMedicalResponseDto;
    }

    @Override
    public void updateEntityFromDto(DossierMedicalRequestDto dto, DossierMedical entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getGroupeSanguin() != null ) {
            entity.setGroupeSanguin( dto.getGroupeSanguin() );
        }
        if ( dto.getAntecedentsMedicaux() != null ) {
            entity.setAntecedentsMedicaux( dto.getAntecedentsMedicaux() );
        }
        if ( dto.getAllergies() != null ) {
            entity.setAllergies( dto.getAllergies() );
        }
        if ( dto.getDernierTraitement() != null ) {
            entity.setDernierTraitement( dto.getDernierTraitement() );
        }
        if ( dto.getObservations() != null ) {
            entity.setObservations( dto.getObservations() );
        }
    }

    @Override
    public List<DossierMedicalResponseDto> toDtoList(List<DossierMedical> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DossierMedicalResponseDto> list = new ArrayList<DossierMedicalResponseDto>( entities.size() );
        for ( DossierMedical dossierMedical : entities ) {
            list.add( toDto( dossierMedical ) );
        }

        return list;
    }

    protected PatientResponseDto patientToPatientResponseDto(Patient patient) {
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

    protected PrescriptionResponseDto prescriptionToPrescriptionResponseDto(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }

        PrescriptionResponseDto prescriptionResponseDto = new PrescriptionResponseDto();

        prescriptionResponseDto.setId( prescription.getId() );
        prescriptionResponseDto.setCreationDate( prescription.getCreationDate() );
        prescriptionResponseDto.setModificationDate( prescription.getModificationDate() );
        prescriptionResponseDto.setTypePrescription( prescription.getTypePrescription() );
        prescriptionResponseDto.setMedicaments( prescription.getMedicaments() );
        prescriptionResponseDto.setInstructions( prescription.getInstructions() );
        prescriptionResponseDto.setDureePrescription( prescription.getDureePrescription() );
        if ( prescription.getQuantite() != null ) {
            prescriptionResponseDto.setQuantite( prescription.getQuantite().longValue() );
        }

        return prescriptionResponseDto;
    }

    protected List<PrescriptionResponseDto> prescriptionListToPrescriptionResponseDtoList(List<Prescription> list) {
        if ( list == null ) {
            return null;
        }

        List<PrescriptionResponseDto> list1 = new ArrayList<PrescriptionResponseDto>( list.size() );
        for ( Prescription prescription : list ) {
            list1.add( prescriptionToPrescriptionResponseDto( prescription ) );
        }

        return list1;
    }

    protected ConsultationResponseDto consultationToConsultationResponseDto(Consultation consultation) {
        if ( consultation == null ) {
            return null;
        }

        ConsultationResponseDto consultationResponseDto = new ConsultationResponseDto();

        consultationResponseDto.setId( consultation.getId() );
        consultationResponseDto.setCreationDate( consultation.getCreationDate() );
        consultationResponseDto.setModificationDate( consultation.getModificationDate() );
        consultationResponseDto.setPoids( consultation.getPoids() );
        consultationResponseDto.setTaille( consultation.getTaille() );
        consultationResponseDto.setTensionArterielle( consultation.getTensionArterielle() );
        consultationResponseDto.setTemperature( consultation.getTemperature() );
        consultationResponseDto.setMotifs( consultation.getMotifs() );
        consultationResponseDto.setDiagnostic( consultation.getDiagnostic() );
        consultationResponseDto.setCompteRendu( consultation.getCompteRendu() );
        consultationResponseDto.setPrescriptions( prescriptionListToPrescriptionResponseDtoList( consultation.getPrescriptions() ) );

        return consultationResponseDto;
    }

    protected List<ConsultationResponseDto> consultationListToConsultationResponseDtoList(List<Consultation> list) {
        if ( list == null ) {
            return null;
        }

        List<ConsultationResponseDto> list1 = new ArrayList<ConsultationResponseDto>( list.size() );
        for ( Consultation consultation : list ) {
            list1.add( consultationToConsultationResponseDto( consultation ) );
        }

        return list1;
    }
}
