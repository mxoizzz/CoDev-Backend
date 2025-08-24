package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.CreateTeamDTO;
import com.codev.backend.dto.TeamDTO;
import com.codev.backend.dto.UpdateTeamDTO;

public interface TeamService {
    TeamDTO createTeam(CreateTeamDTO createTeamDTO, Long memberId);
    TeamDTO getTeamById(Long teamId);
    TeamDTO updateTeam(Long teamId, UpdateTeamDTO updateTeamDTO);
    void removeMember(Long teamId, Long userId);
    public List<TeamDTO> getTeamsByUserId(Long userId);
}
