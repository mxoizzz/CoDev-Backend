package com.codev.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String email;
    private String bio;
    private String profilePicture;
    private List<String> skills;
}
