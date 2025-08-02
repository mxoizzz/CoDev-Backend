package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.CreateProjectDTO;
import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.dto.UpdateProjectDTO;

public interface ProjectService {
    ProjectDTO createProject(CreateProjectDTO createProjectDTO, Long ownerId);
    ProjectDTO getProjectById(Long projectId);
    List<ProjectDTO> getAllProjects();
    ProjectDTO updateProject(Long projectId, UpdateProjectDTO updateProjectDTO, Long userId);
    void deleteProject(Long projectId, Long userId);
}
