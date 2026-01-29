package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.ResponseDto.messageResponseDto.HistoriqueMessageResponseDto;
import com.example.GestionClinique.model.entity.HistoriqueMessage;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:25+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class HistoriqueMessageMapperImpl implements HistoriqueMessageMapper {

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    public HistoriqueMessageResponseDto toDto(HistoriqueMessage entity) {
        if ( entity == null ) {
            return null;
        }

        HistoriqueMessageResponseDto historiqueMessageResponseDto = new HistoriqueMessageResponseDto();

        historiqueMessageResponseDto.setId( entity.getId() );
        historiqueMessageResponseDto.setCreationDate( entity.getCreationDate() );
        historiqueMessageResponseDto.setModificationDate( entity.getModificationDate() );
        historiqueMessageResponseDto.setActeur( utilisateurMapper.toDto( entity.getActeur() ) );
        historiqueMessageResponseDto.setPreviousContent( entity.getPreviousContent() );
        historiqueMessageResponseDto.setNewContent( entity.getNewContent() );

        historiqueMessageResponseDto.setAction( entity.getAction().name() );

        return historiqueMessageResponseDto;
    }
}
