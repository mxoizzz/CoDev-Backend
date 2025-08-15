package com.codev.backend.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.backend.dto.CollaborationRequestDTO;
import com.codev.backend.dto.CreateCollaborationRequestDTO;
import com.codev.backend.dto.CreateTeamDTO;
import com.codev.backend.entity.CollaborationRequest;
import com.codev.backend.entity.Project;
import com.codev.backend.entity.User;
import com.codev.backend.enums.CollaborationStatus;
import com.codev.backend.mapper.CollaborationRequestMapper;
import com.codev.backend.repository.CollaborationRequestRepository;
import com.codev.backend.repository.ProjectRepository;
import com.codev.backend.repository.UserRepository;
import com.codev.backend.service.CollaborationRequestService;
import com.codev.backend.service.TeamService;

@Service
public class CollaborationRequestServiceImpl implements CollaborationRequestService{

    @Autowired
    private CollaborationRequestRepository collaborationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamService teamService;

    @Override
    public CollaborationRequestDTO createRequest(CreateCollaborationRequestDTO requestDTO, Long senderId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(requestDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found")); 
        Project project = projectRepository.findById(requestDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        CollaborationRequest request = new CollaborationRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setProject(project);
        request.setMessage(requestDTO.getMessage());
        request.setStatus(CollaborationStatus.Pending);
        request.setSentAt(LocalDateTime.now());

        CollaborationRequest savedRequest = collaborationRequestRepository.save(request);
        return CollaborationRequestMapper.toDTO(savedRequest);
    }

    @Override
    public List<CollaborationRequestDTO> getReceivedRequests(Long userId) {
        List<CollaborationRequest> requests = collaborationRequestRepository.findAllByReceiverId(userId);
        return requests.stream()
                .map(CollaborationRequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CollaborationRequestDTO> getSentRequests(Long userId) {
        List<CollaborationRequest> requests = collaborationRequestRepository.findAllBySenderId(userId);
        return requests.stream()
                .map(CollaborationRequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateRequestStatus(Long requestId, Long senderId, CollaborationStatus status, Long receiverId) {
        CollaborationRequest request = collaborationRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        if (!request.getReceiver().getId().equals(receiverId)) {
            throw new IllegalStateException("Only the receiver can update the request status");
        }
        if(status == CollaborationStatus.Accepted) {
            request.setStatus(status);
            CreateTeamDTO createTeamDTO = new CreateTeamDTO();
            createTeamDTO.setProjectId(request.getProject().getId());
            createTeamDTO.setLeaderId(request.getReceiver().getId());
            createTeamDTO.setName(null); 
            teamService.createTeam(createTeamDTO, senderId);
        } else if (status == CollaborationStatus.Rejected) {
            request.setStatus(status);
        }
        collaborationRequestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long requestId, Long userId) {
        CollaborationRequest request = collaborationRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        if (!request.getSender().getId().equals(userId) && !request.getReceiver().getId().equals(userId)) {
            throw new IllegalStateException("Only the sender or receiver can delete the request");
        }

        collaborationRequestRepository.delete(request);
    }

}
