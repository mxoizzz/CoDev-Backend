package com.codev.backend.service;

import com.codev.backend.dto.UserLoginDTO;
import com.codev.backend.dto.UserRegisterDTO;
import com.codev.backend.dto.UserUpdateDTO;
import com.codev.backend.entity.User;

public interface UserService {
    User registerUser(UserRegisterDTO userRegisterDTO);
    String loginUser(UserLoginDTO userLoginDTO);
    User getUserById(Long id);
    User updateUser(Long id, UserUpdateDTO userUpdateDTO);
}
