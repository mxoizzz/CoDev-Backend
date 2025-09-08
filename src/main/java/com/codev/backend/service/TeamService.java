package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.CreateTeamDTO;
import com.codev.backend.dto.TeamDTO;
import com.codev.backend.dto.UpdateTeamDTO;

public interface TeamService {
    // Create a New Team
    TeamDTO createTeam(CreateTeamDTO createTeamDTO, Long memberId);
    // Get Team by ID
    TeamDTO getTeamById(Long teamId);
    // Update Team Information
    TeamDTO updateTeam(Long teamId, UpdateTeamDTO updateTeamDTO);
    // Delete a Team
    void removeMember(Long teamId, Long userId);
    // Get Teams by User ID
    public List<TeamDTO> getTeamsByUserId(Long userId);
}
