package com.codev.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TeamDTO {
    private Long id;
    private String name;
    private ProjectDTO project;
    private Set<UserDTO> members;
    private UserDTO leader;
    private LocalDateTime createdAt;
    private String description;
}
