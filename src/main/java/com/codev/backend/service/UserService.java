package com.codev.backend.service;

import com.codev.backend.dto.UserDTO;
import com.codev.backend.dto.UserLoginDTO;
import com.codev.backend.dto.UserRegisterDTO;
import com.codev.backend.dto.UserUpdateDTO;

public interface UserService {
    UserDTO registerUser(UserRegisterDTO userRegisterDTO);
    String loginUser(UserLoginDTO userLoginDTO);
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
}
