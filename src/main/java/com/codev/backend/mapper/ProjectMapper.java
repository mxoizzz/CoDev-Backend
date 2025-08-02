package com.codev.backend.mapper;

import java.time.LocalDateTime;

import com.codev.backend.dto.CreateProjectDTO;
import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.dto.UpdateProjectDTO;
import com.codev.backend.entity.Project;
import com.codev.backend.entity.User;

public class ProjectMapper {
    public Project toProject(CreateProjectDTO createProjectDTO,User user) {
        if (createProjectDTO == null) {
            return null;
        }
        Project project = new Project();
        project.setTitle(createProjectDTO.getTitle());
        project.setDescription(createProjectDTO.getDescription());
        project.setDomain(createProjectDTO.getDomain());
        project.setTechStack(createProjectDTO.getTechStack());
        project.setStatus("Active");
        project.setVisibility(createProjectDTO.getVisibility());
        project.setCreatedAt(LocalDateTime.now());
        project.setOwner(user);
        return project;
    }

    public ProjectDTO projectDTO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setTitle(project.getTitle());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setDomain(project.getDomain());
        projectDTO.setTechStack(project.getTechStack());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setVisibility(project.getVisibility());
        projectDTO.setCreatedAt(project.getCreatedAt());
        if (project.getOwner() != null) {
            projectDTO.setOwner(UserMapper.toDTO(project.getOwner()));
        }

        return projectDTO;
    }

    public void updateProjectFromDTO(Project project, UpdateProjectDTO updateProjectDTO)
    {
        if (updateProjectDTO.getTitle() != null) project.setTitle(updateProjectDTO.getTitle());
        if (updateProjectDTO.getDescription() != null) project.setDescription(updateProjectDTO.getDescription());
        if (updateProjectDTO.getDomain() != null) project.setDomain(updateProjectDTO.getDomain());
        if (updateProjectDTO.getTechStack() != null) project.setTechStack(updateProjectDTO.getTechStack());
        if (updateProjectDTO.getVisibility() != null) project.setVisibility(updateProjectDTO.getVisibility());
        if (updateProjectDTO.getStatus() != null) project.setStatus(updateProjectDTO.getStatus());
    }
}
