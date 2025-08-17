package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.enums.ProjectStatus;

public interface FeedService {

    // Get all public projects
    List<ProjectDTO> getPublicProjects();

    // Search public projects by keyword
    List<ProjectDTO> searchProjects(String keyword);

    // Filter public projects by domain
    List<ProjectDTO> filterByDomain(String domain);

    // Filter public projects by tech stack
    List<ProjectDTO> filterByTechStack(String tech);

    // Filter public projects by status
    List<ProjectDTO> filterByStatus(ProjectStatus status);

    // Combined filter + search for public projects
    List<ProjectDTO> filterAndSearch(String domain, String tech, ProjectStatus status, String keyword);
}
