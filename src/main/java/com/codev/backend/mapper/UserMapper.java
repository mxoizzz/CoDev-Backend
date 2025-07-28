package com.codev.backend.mapper;

import java.time.LocalDateTime;

import com.codev.backend.dto.UserDTO;
import com.codev.backend.dto.UserRegisterDTO;
import com.codev.backend.dto.UserUpdateDTO;
import com.codev.backend.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBio(user.getBio());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setSkills(user.getSkills());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public static User toEntity(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO == null) {
            return null;
        }
        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setBio(userRegisterDTO.getBio());
        user.setProfilePicture(userRegisterDTO.getProfilePicture());
        user.setSkills(userRegisterDTO.getSkills());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    public static void updateUserFromDto(User user, UserUpdateDTO userUpdateDTO) {
        if (user == null || userUpdateDTO == null) {
            return;
        }
        user.setName(userUpdateDTO.getName());
        user.setBio(userUpdateDTO.getBio());
        user.setProfilePicture(userUpdateDTO.getProfilePicture());
        user.setSkills(userUpdateDTO.getSkills());
        user.setUpdatedAt(LocalDateTime.now());
    }
}
