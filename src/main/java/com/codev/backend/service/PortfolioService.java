package com.codev.backend.service;

import com.codev.backend.dto.PortfolioDTO;

public interface PortfolioService {
    // Get Portfolio by User ID
    PortfolioDTO getPortfolioByUserId(Long userId);
}
