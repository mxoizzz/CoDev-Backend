package com.codev.backend.service;

import com.codev.backend.dto.UserDTO;
import com.codev.backend.dto.UserLoginDTO;
import com.codev.backend.dto.UserRegisterDTO;
import com.codev.backend.dto.UserUpdateDTO;

public interface UserService {
    // Registration of New User
    UserDTO registerUser(UserRegisterDTO userRegisterDTO);
    // User Login
    Long loginUser(UserLoginDTO userLoginDTO);
    // Fetch User by ID
    UserDTO getUserById(Long id);
    // Update User Information
    UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
}
