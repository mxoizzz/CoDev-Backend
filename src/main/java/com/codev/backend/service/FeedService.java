package com.codev.backend.service;

import java.util.List;

import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.enums.ProjectStatus;

public interface FeedService {
    List<ProjectDTO> getPublicProjects();
    List<ProjectDTO> searchProjects(String keyword);

    List<ProjectDTO> filterByDomain(String domain);
    List<ProjectDTO> filterByTechStack(String tech);
    List<ProjectDTO> filterByStatus(String status);

    // Combined filter + search
    List<ProjectDTO> filterAndSearch(String domain, String tech, ProjectStatus status, String keyword);

}
