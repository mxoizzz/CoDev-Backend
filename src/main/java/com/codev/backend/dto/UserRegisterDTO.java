package com.codev.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String bio;
    private String profilePicture;
    private List<String> skills; 
}
