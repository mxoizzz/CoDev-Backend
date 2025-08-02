package com.codev.backend.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.backend.dto.CreateProjectDTO;
import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.dto.UpdateProjectDTO;
import com.codev.backend.entity.Project;
import com.codev.backend.entity.User;
import com.codev.backend.mapper.ProjectMapper;
import com.codev.backend.repository.ProjectRepository;
import com.codev.backend.repository.UserRepository;
import com.codev.backend.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ProjectDTO createProject(CreateProjectDTO createProjectDTO, Long ownerId) {
        User user = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + ownerId));
        
        Project project = projectMapper.toProject(createProjectDTO, user);
        Project savedProject = projectRepository.save(project);
        return projectMapper.projectDTO(savedProject);
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        return projectMapper.projectDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(projectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long projectId, UpdateProjectDTO updateProjectDTO, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("User is not the owner of the project");
        }

        projectMapper.updateProjectFromDTO(project, updateProjectDTO);
        Project updatedProject = projectRepository.save(project);
        return projectMapper.projectDTO(updatedProject);
    }

    @Override
    public void deleteProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        if(!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("User is not the owner of the project");
        }
        projectRepository.delete(project);
    }

}
