package com.codev.backend.dto;

import java.util.List;

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
    // private String status;
    private String visibility;
}
