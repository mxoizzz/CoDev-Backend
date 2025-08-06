package com.codev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codev.backend.entity.CollaborationRequest;

public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, Long> {
    
}
