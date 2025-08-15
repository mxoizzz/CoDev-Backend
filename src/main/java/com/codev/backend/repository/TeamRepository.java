package com.codev.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codev.backend.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByProjectId(Long projectId);
    List<Team> findByUserId(Long userId);

}
