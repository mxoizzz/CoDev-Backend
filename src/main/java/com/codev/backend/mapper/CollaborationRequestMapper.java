package com.codev.backend.mapper;

import java.time.LocalDateTime;

import com.codev.backend.dto.CollaborationRequestDTO;
import com.codev.backend.dto.CreateCollaborationRequestDTO;
import com.codev.backend.entity.CollaborationRequest;
import com.codev.backend.entity.Project;
import com.codev.backend.entity.User;
import com.codev.backend.enums.CollaborationStatus;

public class CollaborationRequestMapper {
    public static CollaborationRequest toEntity(CreateCollaborationRequestDTO dto, User sender, User receiver, Project project) {

        if (dto == null) return null;

        CollaborationRequest request = new CollaborationRequest();
        request.setProject(project);
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setMessage(dto.getMessage());
        request.setStatus(CollaborationStatus.Pending);
        request.setSentAt(LocalDateTime.now());
        return request;
    }

    public static CollaborationRequestDTO toDTO(CollaborationRequest request) {

        if (request == null) return null;

        CollaborationRequestDTO dto = new CollaborationRequestDTO();
        dto.setId(request.getId());
        dto.setProject(ProjectMapper.projectDTO(request.getProject()));
        dto.setSender(UserMapper.toDTO(request.getSender()));
        dto.setReceiver(UserMapper.toDTO(request.getReceiver()));
        dto.setMessage(request.getMessage());
        dto.setStatus(request.getStatus());
        dto.setSentAt(request.getSentAt());
        return dto;
    }

    public static void updateStatus(CollaborationRequest request, CollaborationStatus status) {
        if (request != null && status != null) {
            request.setStatus(status);
        }
    }
}
