package com.codev.backend.dto;

import com.codev.backend.enums.CollaborationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCollaborationRequestStatus {
    private CollaborationStatus status;
}
