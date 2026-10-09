package com.finpulse.server.trade.mapper;

import com.finpulse.server.trade.domain.model.Trade;
import com.finpulse.server.trade.dto.TradeRequest;
import com.finpulse.server.trade.dto.TradeResponse;
import org.springframework.stereotype.Component;

@Component
public class TradeMapper {
  public Trade toDomain(TradeRequest request) {
    return Trade.createTrade(
        request.getOrderId(), request.getQuantity(), request.getPrice(), request.getFee());
  }

  public void apply(TradeRequest request, Trade trade) {
    trade.updateTrade(
        request.getOrderId(), request.getQuantity(), request.getPrice(), request.getFee());
  }

  public TradeResponse toResponse(Trade trade) {
    return TradeResponse.builder()
        .tradeId(trade.getId().getValue())
        .orderId(trade.getOrderId().getValue())
        .quantity(trade.getQuantity().getValue())
        .price(trade.getPrice())
        .fee(trade.getFee())
        .executedAt(trade.getExecutedAt())
        .build();
  }
}
