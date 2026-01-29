package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.ResponseDto.stats.StatDuJourResponseDto;
import com.example.GestionClinique.dto.ResponseDto.stats.StatParMoisResponseDto;
import com.example.GestionClinique.dto.ResponseDto.stats.StatsSurLanneeResponseDto;
import com.example.GestionClinique.model.entity.stats.StatDuJour;
import com.example.GestionClinique.model.entity.stats.StatsMois;
import com.example.GestionClinique.model.entity.stats.StatsSurLannee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class StatMapperImpl implements StatMapper {

    @Override
    public StatDuJourResponseDto toStatDuJourDto(StatDuJour entity) {
        if ( entity == null ) {
            return null;
        }

        StatDuJourResponseDto statDuJourResponseDto = new StatDuJourResponseDto();

        statDuJourResponseDto.setId( entity.getId() );
        statDuJourResponseDto.setCreationDate( entity.getCreationDate() );
        statDuJourResponseDto.setModificationDate( entity.getModificationDate() );
        statDuJourResponseDto.setJour( entity.getJour() );
        statDuJourResponseDto.setNbrRendezVousCONFIRME( entity.getNbrRendezVousCONFIRME() );
        statDuJourResponseDto.setNbrRendezANNULE( entity.getNbrRendezANNULE() );
        statDuJourResponseDto.setNbrPatientEnrg( entity.getNbrPatientEnrg() );
        statDuJourResponseDto.setNbrConsultation( entity.getNbrConsultation() );
        statDuJourResponseDto.setRevenu( entity.getRevenu() );

        return statDuJourResponseDto;
    }

    @Override
    public StatParMoisResponseDto toStatParMoisDto(StatsMois entity) {
        if ( entity == null ) {
            return null;
        }

        StatParMoisResponseDto statParMoisResponseDto = new StatParMoisResponseDto();

        statParMoisResponseDto.setId( entity.getId() );
        statParMoisResponseDto.setCreationDate( entity.getCreationDate() );
        statParMoisResponseDto.setModificationDate( entity.getModificationDate() );
        statParMoisResponseDto.setMois( entity.getMois() );
        statParMoisResponseDto.setNbrRendezVousCONFIRME( entity.getNbrRendezVousCONFIRME() );
        statParMoisResponseDto.setNbrRendezANNULE( entity.getNbrRendezANNULE() );
        statParMoisResponseDto.setNbrPatientEnrg( entity.getNbrPatientEnrg() );
        statParMoisResponseDto.setNbrConsultation( entity.getNbrConsultation() );
        statParMoisResponseDto.setRevenu( entity.getRevenu() );

        return statParMoisResponseDto;
    }

    @Override
    public StatsSurLanneeResponseDto toStatsSurLanneeDto(StatsSurLannee entity) {
        if ( entity == null ) {
            return null;
        }

        StatsSurLanneeResponseDto statsSurLanneeResponseDto = new StatsSurLanneeResponseDto();

        statsSurLanneeResponseDto.setAnnee( entity.getAnnee() );
        statsSurLanneeResponseDto.setId( entity.getId() );
        statsSurLanneeResponseDto.setCreationDate( entity.getCreationDate() );
        statsSurLanneeResponseDto.setModificationDate( entity.getModificationDate() );
        statsSurLanneeResponseDto.setNbrRendezVousCONFIRME( entity.getNbrRendezVousCONFIRME() );
        statsSurLanneeResponseDto.setNbrRendezANNULE( entity.getNbrRendezANNULE() );
        statsSurLanneeResponseDto.setNbrPatientEnrg( entity.getNbrPatientEnrg() );
        statsSurLanneeResponseDto.setNbrConsultation( entity.getNbrConsultation() );
        statsSurLanneeResponseDto.setRevenu( entity.getRevenu() );

        return statsSurLanneeResponseDto;
    }
}
