package com.codev.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codev.backend.dto.TeamDTO;
import com.codev.backend.dto.UpdateTeamDTO;
import com.codev.backend.mapper.TeamMapper;
import com.codev.backend.repository.TeamRepository;
import com.codev.backend.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamRepository teamRepository;

    @GetMapping("/{teamId}")
    public TeamDTO getTeam(@PathVariable Long teamId) {
        return teamService.getTeamById(teamId);
    }

    @PutMapping("/{teamId}")
    public TeamDTO updateTeam(@PathVariable Long teamId,
                              @RequestBody UpdateTeamDTO updateTeamDTO) {
        return teamService.updateTeam(teamId, updateTeamDTO);
    }

    @DeleteMapping("/{teamId}/members/{userId}")
    public void removeMember(@PathVariable Long teamId,
                             @PathVariable Long userId) {
        teamService.removeMember(teamId, userId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TeamDTO>> getTeamsByUserId(@PathVariable Long userId) {
        List<TeamDTO> teams = teamService.getTeamsByUserId(userId);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<TeamDTO> getTeamByProject(@PathVariable Long projectId) {
        return teamRepository.findByProjectId(projectId)
                .map(team -> ResponseEntity.ok(TeamMapper.toDto(team)))
                .orElseGet(() -> ResponseEntity.noContent().build()); // <-- no team, safe
}


}
