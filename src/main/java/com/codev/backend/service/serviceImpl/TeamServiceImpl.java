package com.codev.backend.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

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
    // Fetch project
    Project project = projectRepository.findById(createTeamDTO.getProjectId())
            .orElseThrow(() -> new RuntimeException("Project not found"));

    // Fetch leader
    User leader = userRepository.findById(createTeamDTO.getLeaderId())
            .orElseThrow(() -> new RuntimeException("Leader not found"));

    // Check if a team already exists for this project
    Optional<Team> existingTeam = teamRepository.findByProjectId(project.getId());
    if (existingTeam.isPresent()) {
        Team team = existingTeam.get();
        User newMember = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        team.getMembers().add(newMember);
        team = teamRepository.save(team);
        return TeamMapper.toDto(team);
    }

    // Create new team
    Team team = new Team();
    team.setProject(project);
    team.setLeader(leader);
    team.setMembers(new HashSet<>()); // Initialize members set
    team.getMembers().add(leader);   // Leader is automatically a member
    team.setCreatedAt(LocalDateTime.now());

    // Set default name if not provided
    if (createTeamDTO.getName() == null || createTeamDTO.getName().trim().isEmpty()) {
        team.setName(project.getTitle() + "'s Team");
    } else {
        team.setName(createTeamDTO.getName());
    }

    // Save and return
    Team savedTeam = teamRepository.save(team);
    return TeamMapper.toDto(savedTeam);
}

    @Override
    public TeamDTO getTeamById(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTeamById'");
    }

    @Override
    public TeamDTO updateTeam(Long teamId, UpdateTeamDTO updateTeamDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTeam'");
    }

    @Override
    public void removeMember(Long teamId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }
}
