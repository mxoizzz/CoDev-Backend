package com.codev.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codev.backend.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByProjectId(Long projectId);
    @Query("SELECT t FROM Team t WHERE t.leader.id = :userId OR EXISTS (SELECT 1 FROM t.members m WHERE m.id = :userId)")
    List<Team> findByUserId(@Param("userId") Long userId);
}
