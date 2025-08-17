package com.codev.backend.controller;

import com.codev.backend.dto.ProjectDTO;
import com.codev.backend.enums.ProjectStatus;
import com.codev.backend.service.FeedService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    // ✅ Single endpoint for feed with filters + search
    @GetMapping
    public List<ProjectDTO> getProjects(
            @RequestParam(required = false) String domain,
            @RequestParam(required = false) String tech,
            @RequestParam(required = false) ProjectStatus status,
            @RequestParam(required = false) String keyword
    ) {
        if (domain == null && tech == null && status == null && keyword == null) {
            return feedService.getPublicProjects();
        }

        if (keyword != null && domain == null && tech == null && status == null) {
            return feedService.searchProjects(keyword);
        }

        if (domain != null && tech == null && status == null && keyword == null) {
            return feedService.filterByDomain(domain);
        }

        if (tech != null && domain == null && status == null && keyword == null) {
            return feedService.filterByTechStack(tech);
        }

        if (status != null && domain == null && tech == null && keyword == null) {
            return feedService.filterByStatus(status);
        }

        return feedService.filterAndSearch(domain, tech, status, keyword);
    }
}

