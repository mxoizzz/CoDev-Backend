package com.codev.backend.service;

import com.codev.backend.dto.PortfolioDTO;

public interface PortfolioService {
    PortfolioDTO getPortfolioByUserId(Long userId);
}
