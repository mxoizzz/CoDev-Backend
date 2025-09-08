package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.CreateProjectDTO;
import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.dto.UpdateProjectDTO;

public interface ProjectService {
    // Create a New Project
    ProjectDTO createProject(CreateProjectDTO createProjectDTO, Long ownerId);
    // Get Project by ID
    ProjectDTO getProjectById(Long projectId);
    // Get All Projects
    List<ProjectDTO> getAllProjects();
    // Update Project Information
    ProjectDTO updateProject(Long projectId, UpdateProjectDTO updateProjectDTO, Long userId);
    // Delete a Project
    void deleteProject(Long projectId, Long userId);
}
