package com.codev.backend.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.backend.dto.CreateTeamDTO;
import com.codev.backend.dto.TeamDTO;
import com.codev.backend.dto.UpdateTeamDTO;
import com.codev.backend.entity.Project;
import com.codev.backend.entity.Team;
import com.codev.backend.entity.User;
import com.codev.backend.mapper.TeamMapper;
import com.codev.backend.repository.ProjectRepository;
import com.codev.backend.repository.TeamRepository;
import com.codev.backend.repository.UserRepository;
import com.codev.backend.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamDTO createTeam(CreateTeamDTO createTeamDTO, Long memberId) {
        Project project = projectRepository.findById(createTeamDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User leader = userRepository.findById(createTeamDTO.getLeaderId())
                .orElseThrow(() -> new RuntimeException("Leader not found"));

        Optional<Team> existingTeam = teamRepository.findByProjectId(project.getId());
        if (existingTeam.isPresent()) {
            Team team = existingTeam.get();
            User newMember = userRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            team.getMembers().add(newMember);
            team = teamRepository.save(team);
            return TeamMapper.toDto(team);
        }

        Team team = new Team();
        team.setProject(project);
        team.setLeader(leader);
        team.setMembers(new HashSet<>()); 
        team.getMembers().add(leader); 
        User newMember = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        team.getMembers().add(newMember);  
        team.setCreatedAt(LocalDateTime.now());

        if (createTeamDTO.getName() == null || createTeamDTO.getName().trim().isEmpty()) {
            team.setName(project.getTitle() + "'s Team");
        } else {
            team.setName(createTeamDTO.getName());
        }

        Team savedTeam = teamRepository.save(team);
        return TeamMapper.toDto(savedTeam);
    }

    @Override
    public TeamDTO getTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return TeamMapper.toDto(team);
    }

    @Override
    public TeamDTO updateTeam(Long teamId, UpdateTeamDTO updateTeamDTO) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        if (updateTeamDTO.getName() != null && !updateTeamDTO.getName().trim().isEmpty()) {
            team.setName(updateTeamDTO.getName());
        }
        if (updateTeamDTO.getDescription() != null) {
            team.setDescription(updateTeamDTO.getDescription());
        }
        if (updateTeamDTO.getStatus() != null) {
            team.getProject().setStatus(updateTeamDTO.getStatus());
            projectRepository.save(team.getProject());
        }
        Team updatedTeam = teamRepository.save(team);
        return TeamMapper.toDto(updatedTeam);
    }

    @Override
    public void removeMember(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        User member = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (team.getLeader().getId().equals(userId)) {
            throw new IllegalStateException("Cannot remove the team leader");
        }

        if (!team.getMembers().remove(member)) {
            throw new IllegalStateException("User is not a member of the team");
        }

        teamRepository.save(team);
    }

    @Override
    public List<TeamDTO> getTeamsByUserId(Long userId) {
        List<Team> teams = teamRepository.findByUserId(userId);
        return teams.stream()
                .map(TeamMapper::toDto)   
                .collect(Collectors.toList());
}


}
