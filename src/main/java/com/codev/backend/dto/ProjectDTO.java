package com.codev.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    private String status;
    private String visibility;
    private UserDTO owner;
}
