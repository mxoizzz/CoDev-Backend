package com.codev.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateCollaborationRequestDTO {
    private Long projectId;
    private Long receiverId;
    private String message; 
}
