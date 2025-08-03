package com.codev.backend.dto;


import java.time.LocalDateTime;

import com.codev.backend.enums.CollaborationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CollaborationRequestDTO {
    private Long id;
    private UserDTO sender;
    private UserDTO receiver;
    private ProjectDTO project;
    private String message;
    private CollaborationStatus status;
    private LocalDateTime sentAt;
}
