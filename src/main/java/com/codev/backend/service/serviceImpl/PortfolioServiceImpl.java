package com.codev.backend.service.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.backend.dto.PortfolioDTO;
import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.dto.UserDTO;
import com.codev.backend.entity.Project;
import com.codev.backend.entity.Team;
import com.codev.backend.entity.User;
import com.codev.backend.mapper.ProjectMapper;
import com.codev.backend.mapper.UserMapper;
import com.codev.backend.repository.ProjectRepository;
import com.codev.backend.repository.TeamRepository;
import com.codev.backend.repository.UserRepository;
import com.codev.backend.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public PortfolioDTO getPortfolioByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<Project> projects = projectRepository.findAllByOwnerId(userId);

        UserDTO userDTO = UserMapper.toDTO(user);
            
        List<Team> teams = teamRepository.findByMemberId(userId);
        List<Project> memberProjects = teams.stream()
            .map(Team::getProject)
            .collect(Collectors.toList());

        // Merge owned and member projects
        Set<Project> allProjects = new HashSet<>();
        allProjects.addAll(projects); // owned projects
        allProjects.addAll(memberProjects); // member projects

        List<ProjectDTO> projectDTOs = allProjects.stream()
            .map(ProjectMapper::projectDTO)
            .collect(Collectors.toList());


        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setUser(userDTO);
        portfolioDTO.setProjects(projectDTOs);
        return portfolioDTO; 
    }
}
