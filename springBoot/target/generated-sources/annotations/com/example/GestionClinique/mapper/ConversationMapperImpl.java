package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.messageRequestDto.ConversationRequestDto;
import com.example.GestionClinique.dto.ResponseDto.UtilisateurResponseDto;
import com.example.GestionClinique.dto.ResponseDto.messageResponseDto.ConversationResponseDto;
import com.example.GestionClinique.model.entity.Conversation;
import com.example.GestionClinique.model.entity.ConversationParticipant;
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
public class ConversationMapperImpl extends ConversationMapper {

    @Autowired
    private GroupeMapper groupeMapper;

    @Override
    public Conversation toEntity(ConversationRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Conversation conversation = new Conversation();

        conversation.setParticipants( mapParticipantIdsToParticipants( dto.getParticipantIds() ) );
        conversation.setTypeConversation( dto.getTypeConversation() );
        conversation.setTitre( dto.getTitre() );

        return conversation;
    }

    @Override
    public ConversationResponseDto toDto(Conversation entity) {
        if ( entity == null ) {
            return null;
        }

        ConversationResponseDto conversationResponseDto = new ConversationResponseDto();

        conversationResponseDto.setTypeConversation( entity.getTypeConversation() );
        conversationResponseDto.setParticipants( conversationParticipantListToUtilisateurResponseDtoList( entity.getParticipants() ) );
        conversationResponseDto.setLastMessageAt( entity.getLastMessageAt() );
        conversationResponseDto.setId( entity.getId() );
        conversationResponseDto.setCreationDate( entity.getCreationDate() );
        conversationResponseDto.setModificationDate( entity.getModificationDate() );
        conversationResponseDto.setTitre( entity.getTitre() );
        conversationResponseDto.setGroupe( groupeMapper.toDto( entity.getGroupe() ) );

        return conversationResponseDto;
    }

    @Override
    public List<ConversationResponseDto> toDtoList(List<Conversation> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ConversationResponseDto> list = new ArrayList<ConversationResponseDto>( entities.size() );
        for ( Conversation conversation : entities ) {
            list.add( toDto( conversation ) );
        }

        return list;
    }

    protected List<UtilisateurResponseDto> conversationParticipantListToUtilisateurResponseDtoList(List<ConversationParticipant> list) {
        if ( list == null ) {
            return null;
        }

        List<UtilisateurResponseDto> list1 = new ArrayList<UtilisateurResponseDto>( list.size() );
        for ( ConversationParticipant conversationParticipant : list ) {
            list1.add( map( conversationParticipant ) );
        }

        return list1;
    }
}
