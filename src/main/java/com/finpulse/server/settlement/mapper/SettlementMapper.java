package com.finpulse.server.settlement.mapper;

import com.finpulse.server.settlement.domain.model.Settlement;
import com.finpulse.server.settlement.dto.SettlementRequest;
import com.finpulse.server.settlement.dto.SettlementResponse;
import org.springframework.stereotype.Component;

@Component
public class SettlementMapper {
  public Settlement toDomain(SettlementRequest request) {
    return Settlement.createSettlement(
        request.getTradeId(),
        request.getPaymentId(),
        request.getStatus(),
        request.getSettledAt());
  }

  public void apply(SettlementRequest request, Settlement settlement) {
    settlement.updateSettlement(
        request.getTradeId(),
        request.getPaymentId(),
        request.getStatus(),
        request.getSettledAt());
  }

  public SettlementResponse toResponse(Settlement settlement) {
    return SettlementResponse.builder()
        .settlementId(settlement.getId().getValue())
        .tradeId(settlement.getTradeId().getValue())
        .paymentId(settlement.getPaymentId().getValue())
        .status(settlement.getStatus().name())
        .settledAt(settlement.getSettledAt())
        .build();
  }
}
