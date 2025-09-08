package com.codev.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String bio;
    private String profilePicture;
    private List<String> skills;
    private LocalDateTime createdAt;
    private String linkedin;
    private String github;
    private String instagram;
    private String website;
}
