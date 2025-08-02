package com.codev.backend.dto;

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
public class CreateProjectDTO {
    private String title;
    private String description;
    private String domain;
    private List<String> techStack;
    private ProjectStatus status;
    private ProjectVisibility visibility;
}
