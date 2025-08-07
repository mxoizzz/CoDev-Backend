package com.codev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codev.backend.entity.CollaborationRequest;

public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, Long> {
    List<CollaborationRequest> findAllByReceiverId(Long receiverId);
    List<CollaborationRequest> findAllBySenderId(Long senderId);    
}
