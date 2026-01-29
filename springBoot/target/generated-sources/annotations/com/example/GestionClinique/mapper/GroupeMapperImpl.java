package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.messageRequestDto.GroupeRequestDto;
import com.example.GestionClinique.dto.ResponseDto.messageResponseDto.GroupeResponseDto;
import com.example.GestionClinique.model.entity.Groupe;
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
public class GroupeMapperImpl implements GroupeMapper {

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    public Groupe toEntity(GroupeRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Groupe groupe = new Groupe();

        groupe.setNom( dto.getNom() );
        groupe.setDescription( dto.getDescription() );

        return groupe;
    }

    @Override
    public GroupeResponseDto toDto(Groupe entity) {
        if ( entity == null ) {
            return null;
        }

        GroupeResponseDto groupeResponseDto = new GroupeResponseDto();

        groupeResponseDto.setCreateur( utilisateurMapper.toDto( entity.getCreateur() ) );
        groupeResponseDto.setId( entity.getId() );
        groupeResponseDto.setCreationDate( entity.getCreationDate() );
        groupeResponseDto.setModificationDate( entity.getModificationDate() );
        groupeResponseDto.setNom( entity.getNom() );
        groupeResponseDto.setDescription( entity.getDescription() );
        groupeResponseDto.setMembres( utilisateurMapper.toDtoList( entity.getMembres() ) );

        return groupeResponseDto;
    }

    @Override
    public List<GroupeResponseDto> toDtoList(List<Groupe> entities) {
        if ( entities == null ) {
            return null;
        }

        List<GroupeResponseDto> list = new ArrayList<GroupeResponseDto>( entities.size() );
        for ( Groupe groupe : entities ) {
            list.add( toDto( groupe ) );
        }

        return list;
    }
}
