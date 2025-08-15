package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.CollaborationRequestDTO;
import com.codev.backend.dto.CreateCollaborationRequestDTO;
import com.codev.backend.enums.CollaborationStatus;

public interface CollaborationRequestService {
    CollaborationRequestDTO createRequest(CreateCollaborationRequestDTO requestDTO, Long senderId);

    List<CollaborationRequestDTO> getReceivedRequests(Long userId);

    List<CollaborationRequestDTO> getSentRequests(Long userId);

    void updateRequestStatus(Long requestId,Long senderId, CollaborationStatus status, Long receiverId);

    void deleteRequest(Long requestId, Long userId);    
}
