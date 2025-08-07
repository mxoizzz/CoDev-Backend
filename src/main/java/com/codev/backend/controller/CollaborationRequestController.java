package com.codev.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codev.backend.dto.CollaborationRequestDTO;
import com.codev.backend.dto.CreateCollaborationRequestDTO;
import com.codev.backend.enums.CollaborationStatus;
import com.codev.backend.service.CollaborationRequestService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/collaboration-requests")
@RequiredArgsConstructor
public class CollaborationRequestController {
    private final CollaborationRequestService collaborationRequestService;

    @PostMapping("/create")
    public ResponseEntity<CollaborationRequestDTO> createRequest(
            @RequestBody CreateCollaborationRequestDTO requestDTO,
            @RequestParam Long senderId) {
        CollaborationRequestDTO createdRequest = collaborationRequestService.createRequest(requestDTO, senderId);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<CollaborationRequestDTO>> getReceivedRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(collaborationRequestService.getReceivedRequests(userId));
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<CollaborationRequestDTO>> getSentRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(collaborationRequestService.getSentRequests(userId));
    }

    @PutMapping("/{requestId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long requestId,
            @RequestParam CollaborationStatus status,
            @RequestParam Long receiverId) {
        collaborationRequestService.updateRequestStatus(requestId, status, receiverId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(
            @PathVariable Long requestId,
            @RequestParam Long userId) {
        collaborationRequestService.deleteRequest(requestId, userId);
        return ResponseEntity.noContent().build();
    }
    
}
