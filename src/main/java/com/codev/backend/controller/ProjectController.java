package com.codev.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codev.backend.dto.CreateProjectDTO;
import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.dto.UpdateProjectDTO;
import com.codev.backend.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create/{ownerId}")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO, @PathVariable Long ownerId) {
        ProjectDTO project = projectService.createProject(createProjectDTO, ownerId);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long projectId) {
        ProjectDTO project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{projectId}/update/{userId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long projectId, @RequestBody UpdateProjectDTO updateProjectDTO, @PathVariable Long userId) {
        ProjectDTO updatedProject = projectService.updateProject(projectId, updateProjectDTO, userId);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}/delete/{userId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.deleteProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
