package com.codev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codev.backend.entity.CollaborationRequest;
import com.codev.backend.entity.Project;

public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, Long> {
    List<CollaborationRequest> findAllByReceiverId(Long receiverId);
    List<CollaborationRequest> findAllBySenderId(Long senderId);
    @Modifying
    @Query("DELETE FROM CollaborationRequest cr WHERE cr.project = :project")
    void deleteAllByProject(Project project);    
}
