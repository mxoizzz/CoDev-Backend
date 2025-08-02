package com.codev.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.codev.backend.enums.ProjectStatus;
import com.codev.backend.enums.ProjectVisibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String domain;
    private List<String> techStack;
    private LocalDateTime createdAt;
    private ProjectStatus status;
    private ProjectVisibility visibility;
    private UserDTO owner;
}
