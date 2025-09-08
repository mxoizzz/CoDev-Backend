package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.CollaborationRequestDTO;
import com.codev.backend.dto.CreateCollaborationRequestDTO;
import com.codev.backend.enums.CollaborationStatus;

public interface CollaborationRequestService {
    // Create a New Collaboration Request
    CollaborationRequestDTO createRequest(CreateCollaborationRequestDTO requestDTO, Long senderId);
    // Get Received Requests by User ID
    List<CollaborationRequestDTO> getReceivedRequests(Long userId);
    // Get Sent Requests by User ID
    List<CollaborationRequestDTO> getSentRequests(Long userId);
    // Update Request Status (Accept/Reject)
    void updateRequestStatus(Long requestId,Long senderId, CollaborationStatus status, Long receiverId);
    // Delete a Collaboration Request
    void deleteRequest(Long requestId, Long userId);    
}
