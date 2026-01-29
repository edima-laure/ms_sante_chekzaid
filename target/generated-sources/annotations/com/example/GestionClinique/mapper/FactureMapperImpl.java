package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.FactureRequestDto;
import com.example.GestionClinique.dto.ResponseDto.FactureResponseDto;
import com.example.GestionClinique.model.entity.Facture;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class FactureMapperImpl implements FactureMapper {

    private final DatatypeFactory datatypeFactory;

    public FactureMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public Facture toEntity(FactureRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Facture facture = new Facture();

        facture.setMontant( dto.getMontant() );
        facture.setDateEmission( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( dto.getDateEmission() ) ) );
        facture.setStatutPaiement( dto.getStatutPaiement() );
        facture.setModePaiement( dto.getModePaiement() );

        return facture;
    }

    @Override
    public FactureResponseDto toDto(Facture entity) {
        if ( entity == null ) {
            return null;
        }

        FactureResponseDto factureResponseDto = new FactureResponseDto();

        factureResponseDto.setId( entity.getId() );
        factureResponseDto.setCreationDate( entity.getCreationDate() );
        factureResponseDto.setModificationDate( entity.getModificationDate() );
        factureResponseDto.setMontant( entity.getMontant() );
        factureResponseDto.setDateEmission( entity.getDateEmission() );
        factureResponseDto.setStatutPaiement( entity.getStatutPaiement() );
        factureResponseDto.setModePaiement( entity.getModePaiement() );

        factureResponseDto.setPatientNomComplet( entity.getPatient() != null ? entity.getPatient().getNom() + " " + entity.getPatient().getPrenom() : null );
        factureResponseDto.setServiceMedicalNom( getServiceMedicalName(entity) );

        return factureResponseDto;
    }

    @Override
    public List<FactureResponseDto> toDtoList(List<Facture> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FactureResponseDto> list = new ArrayList<FactureResponseDto>( entities.size() );
        for ( Facture facture : entities ) {
            list.add( toDto( facture ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(FactureRequestDto dto, Facture entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMontant() != null ) {
            entity.setMontant( dto.getMontant() );
        }
        if ( dto.getDateEmission() != null ) {
            entity.setDateEmission( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( dto.getDateEmission() ) ) );
        }
        if ( dto.getStatutPaiement() != null ) {
            entity.setStatutPaiement( dto.getStatutPaiement() );
        }
        if ( dto.getModePaiement() != null ) {
            entity.setModePaiement( dto.getModePaiement() );
        }
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }
}
