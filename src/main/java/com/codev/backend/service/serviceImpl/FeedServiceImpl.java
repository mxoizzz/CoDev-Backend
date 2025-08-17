package com.codev.backend.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.enums.ProjectStatus;
import com.codev.backend.enums.ProjectVisibility;
import com.codev.backend.mapper.ProjectMapper;
import com.codev.backend.repository.ProjectRepository;
import com.codev.backend.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

    private final ProjectRepository projectRepository;

    public FeedServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDTO> getPublicProjects() {
        return projectRepository.findByVisibility(ProjectVisibility.Public).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> searchProjects(String keyword) {
        return projectRepository.searchPublicProjects(ProjectVisibility.Public, keyword).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterByDomain(String domain) {
        return projectRepository.findByVisibilityAndDomainIgnoreCase(ProjectVisibility.Public, domain).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterByTechStack(String tech) {
        return projectRepository.filterByTechStack(ProjectVisibility.Public, tech).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterByStatus(ProjectStatus status) {
        return projectRepository.findByStatus(status).stream()
                .filter(p -> ProjectVisibility.Public.equals(p.getVisibility()))
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterAndSearch(String domain, String tech, ProjectStatus status, String keyword) {
        return projectRepository.filterAndSearch(ProjectVisibility.Public, domain, tech, status, keyword).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }
}
