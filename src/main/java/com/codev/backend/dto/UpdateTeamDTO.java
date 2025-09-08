package com.codev.backend.dto;

import com.codev.backend.enums.ProjectStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateTeamDTO {
    private String name;
    private String description;
    private ProjectStatus status;
}
