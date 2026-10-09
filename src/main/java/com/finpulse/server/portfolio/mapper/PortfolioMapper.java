package com.finpulse.server.portfolio.mapper;

import com.finpulse.server.portfolio.domain.model.Portfolio;
import com.finpulse.server.portfolio.dto.PortfolioRequest;
import com.finpulse.server.portfolio.dto.PortfolioResponse;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {
  public Portfolio toDomain(PortfolioRequest request) {
    return Portfolio.createPortfolio(
        request.getAccountId(), request.getName(), request.getBaseCurrency());
  }

  public void apply(PortfolioRequest request, Portfolio portfolio) {
    portfolio.updatePortfolio(
        request.getAccountId(), request.getName(), request.getBaseCurrency());
  }

  public PortfolioResponse toResponse(Portfolio portfolio) {
    return PortfolioResponse.builder()
        .portfolioId(portfolio.getId().getValue())
        .accountId(portfolio.getAccountId().getValue())
        .name(portfolio.getName())
        .baseCurrency(portfolio.getBaseCurrency().getCode())
        .createdAt(portfolio.getCreatedAt())
        .build();
  }
}
