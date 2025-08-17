package com.codev.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codev.backend.dto.PortfolioDTO;
import com.codev.backend.service.PortfolioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public PortfolioDTO getPortfolioByUserId(@PathVariable Long userId) {
        return portfolioService.getPortfolioByUserId(userId);
    }
}
