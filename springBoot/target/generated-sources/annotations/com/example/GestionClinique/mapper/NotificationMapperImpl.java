package com.example.GestionClinique.mapper;

import com.example.GestionClinique.dto.RequestDto.NotificationRequestDto;
import com.example.GestionClinique.dto.ResponseDto.NotificationResponseDto;
import com.example.GestionClinique.model.entity.Message;
import com.example.GestionClinique.model.entity.Notification;
import com.example.GestionClinique.model.entity.RendezVous;
import com.example.GestionClinique.model.entity.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T12:51:25+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationResponseDto toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();

        notificationResponseDto.setUtilisateurId( notificationUtilisateurId( notification ) );
        notificationResponseDto.setMessageId( notificationMessageId( notification ) );
        notificationResponseDto.setRendezVousId( notificationRendezVousId( notification ) );
        notificationResponseDto.setId( notification.getId() );
        notificationResponseDto.setCreationDate( notification.getCreationDate() );
        notificationResponseDto.setModificationDate( notification.getModificationDate() );
        notificationResponseDto.setContenu( notification.getContenu() );
        notificationResponseDto.setType( notification.getType() );
        notificationResponseDto.setLu( notification.isLu() );
        notificationResponseDto.setDateCreation( notification.getDateCreation() );

        return notificationResponseDto;
    }

    @Override
    public Notification toEntity(NotificationRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setType( dto.getType() );
        notification.setContenu( dto.getContenu() );

        return notification;
    }

    @Override
    public List<NotificationResponseDto> toDtos(List<Notification> notifications) {
        if ( notifications == null ) {
            return null;
        }

        List<NotificationResponseDto> list = new ArrayList<NotificationResponseDto>( notifications.size() );
        for ( Notification notification : notifications ) {
            list.add( toDto( notification ) );
        }

        return list;
    }

    private Long notificationUtilisateurId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        Utilisateur utilisateur = notification.getUtilisateur();
        if ( utilisateur == null ) {
            return null;
        }
        Long id = utilisateur.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long notificationMessageId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        Message message = notification.getMessage();
        if ( message == null ) {
            return null;
        }
        Long id = message.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long notificationRendezVousId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        RendezVous rendezVous = notification.getRendezVous();
        if ( rendezVous == null ) {
            return null;
        }
        Long id = rendezVous.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
