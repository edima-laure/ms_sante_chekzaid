package com.example.GestionClinique.controller;

import com.example.GestionClinique.dto.RequestDto.messageRequestDto.ConversationRequestDto;
import com.example.GestionClinique.dto.RequestDto.messageRequestDto.GroupeRequestDto;
import com.example.GestionClinique.dto.RequestDto.messageRequestDto.MessageRequestDto;
import com.example.GestionClinique.dto.ResponseDto.messageResponseDto.ConversationResponseDto;
import com.example.GestionClinique.dto.ResponseDto.messageResponseDto.GroupeResponseDto;
import com.example.GestionClinique.dto.ResponseDto.messageResponseDto.MessageResponseDto;
import com.example.GestionClinique.mapper.ConversationMapper;
import com.example.GestionClinique.mapper.GroupeMapper;
import com.example.GestionClinique.mapper.MessageMapper;
import com.example.GestionClinique.model.entity.Conversation;
import com.example.GestionClinique.model.entity.Groupe;
import com.example.GestionClinique.model.entity.Message;
import com.example.GestionClinique.model.entity.Utilisateur;
import com.example.GestionClinique.service.serviceImpl.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.GestionClinique.configuration.utils.Constants.API_NAME;

@Tag(name = "Gestion du chat", description = "API pour la gestion du chat de la clinique")
@RestController
@RequestMapping(API_NAME + "/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final MessageMapper messageMapper;
    private final ConversationMapper conversationMapper;
    private final GroupeMapper groupeMapper;

    // ================= UTIL =================

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Utilisateur utilisateur) {
            return utilisateur.getId();
        }

        throw new IllegalStateException(
                "Utilisateur authentifié introuvable dans le contexte de sécurité"
        );
    }

    // ================= MESSAGES =================

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @PostMapping("/messages")
    public ResponseEntity<MessageResponseDto> sendMessage(
            @Valid @RequestBody MessageRequestDto messageDto
    ) {
        Long senderId = getAuthenticatedUserId();
        Message message = chatService.sendMessage(messageDto, senderId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(messageMapper.toDto(message));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @PutMapping("/messages/{id}")
    public ResponseEntity<MessageResponseDto> updateMessage(
            @PathVariable Long id,
            @RequestBody String newContent
    ) {
        Long userId = getAuthenticatedUserId();
        Message message = chatService.updateMessage(id, newContent, userId);
        return ResponseEntity.ok(messageMapper.toDto(message));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        Long userId = getAuthenticatedUserId();
        chatService.deleteMessage(id, userId);
        return ResponseEntity.noContent().build();
    }

    // ================= CONVERSATIONS =================

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @GetMapping("/conversations/{id}/messages")
    public ResponseEntity<Page<MessageResponseDto>> getMessagesByConversation(
            @PathVariable("id") Long conversationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Long userId = getAuthenticatedUserId();

        if (!chatService.isUserInConversation(conversationId, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Page<Message> messages =
                chatService.getMessagesByConversation(conversationId, page, size);

        return ResponseEntity.ok(messages.map(messageMapper::toDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @GetMapping("/conversations")
    public ResponseEntity<List<ConversationResponseDto>> getUserConversations() {
        Long userId = getAuthenticatedUserId();
        List<Conversation> conversations = chatService.getUserConversations(userId);
        return ResponseEntity.ok(conversationMapper.toDtoList(conversations));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @PostMapping("/conversations")
    public ResponseEntity<ConversationResponseDto> startConversation(
            @Valid @RequestBody ConversationRequestDto conversationDto
    ) {
        Conversation conversation = chatService.startConversation(conversationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversationMapper.toDto(conversation));
    }

    // ================= GROUPES =================

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETAIRE', 'MEDECIN')")
    @PostMapping("/groups")
    public ResponseEntity<GroupeResponseDto> createGroup(
            @Valid @RequestBody GroupeRequestDto groupeDto
    ) {
        Groupe groupe = chatService.createGroup(groupeDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(groupeMapper.toDto(groupe));
    }
}
