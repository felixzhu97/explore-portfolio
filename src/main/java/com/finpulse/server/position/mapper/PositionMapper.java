package com.finpulse.server.position.mapper;

import com.finpulse.server.position.domain.model.Position;
import com.finpulse.server.position.dto.PositionRequest;
import com.finpulse.server.position.dto.PositionResponse;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {
  public Position toDomain(PositionRequest request) {
    return Position.createPosition(
        request.getPortfolioId(),
        request.getInstrumentId(),
        request.getQuantity(),
        request.getCostBasis());
  }

  public void apply(PositionRequest request, Position position) {
    position.updatePosition(
        request.getPortfolioId(),
        request.getInstrumentId(),
        request.getQuantity(),
        request.getCostBasis());
  }

  public PositionResponse toResponse(Position position) {
    return PositionResponse.builder()
        .positionId(position.getId().getValue())
        .portfolioId(position.getPortfolioId().getValue())
        .instrumentId(position.getInstrumentId().getValue())
        .quantity(position.getQuantity().getValue())
        .costBasis(position.getCostBasis())
        .asOfDate(position.getAsOfDate())
        .build();
  }
}
