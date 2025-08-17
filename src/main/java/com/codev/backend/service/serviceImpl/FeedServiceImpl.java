package com.codev.backend.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.mapper.ProjectMapper;
import com.codev.backend.repository.ProjectRepository;
import com.codev.backend.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService{

    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getPublicProjects() {
        return projectRepository.findByVisibility("Public").stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> searchProjects(String keyword) {
        return projectRepository.searchPublicProjects(keyword).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterByDomain(String domain) {
        return projectRepository.findByVisibilityAndDomainIgnoreCase("Public", domain).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterByTechStack(String tech) {
        return projectRepository.filterByTechStack(tech).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterByStatus(String status) {
        return projectRepository.findByStatus(status).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> filterAndSearch(String domain, String tech, String keyword) {
        return projectRepository.filterAndSearch(domain, tech, keyword).stream()
                .map(ProjectMapper::projectDTO)
                .collect(Collectors.toList());
    }

}
