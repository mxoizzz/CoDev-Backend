package com.codev.backend.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.backend.dto.UserDTO;
import com.codev.backend.dto.UserLoginDTO;
import com.codev.backend.dto.UserRegisterDTO;
import com.codev.backend.dto.UserUpdateDTO;
import com.codev.backend.entity.User;
import com.codev.backend.mapper.UserMapper;
import com.codev.backend.repository.UserRepository;
import com.codev.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = UserMapper.toEntity(userRegisterDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public Long loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDTO.getEmail());
        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(userLoginDTO.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userOptional.get();
        Long id = user.getId();
        return id;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserMapper.updateUserFromDto(user, userUpdateDTO);
        user.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }

}
