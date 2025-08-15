package com.codev.backend.mapper;

import java.util.stream.Collectors;

import com.codev.backend.dto.TeamDTO;
import com.codev.backend.entity.Team;

public class TeamMapper {
    public static TeamDTO toDto(Team team) {
        if (team == null) return null;

        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());    
        dto.setProject(ProjectMapper.projectDTO(team.getProject()));
        dto.setMembers(team.getMembers().stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toSet()));
        dto.setLeader(UserMapper.toDTO(team.getLeader()));
        dto.setCreatedAt(team.getCreatedAt());
        return dto;
    }  

    public static Team toEntity(TeamDTO teamDTO) {
        if (teamDTO == null) return null;

        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setProject(ProjectMapper.toProject(teamDTO.getProject()));
        team.setMembers(teamDTO.getMembers().stream()
            .map(UserMapper::toEntity)
            .collect(Collectors.toSet()));
        team.setLeader(UserMapper.toEntity(teamDTO.getLeader()));
        team.setCreatedAt(teamDTO.getCreatedAt());
        return team;
    }
}
