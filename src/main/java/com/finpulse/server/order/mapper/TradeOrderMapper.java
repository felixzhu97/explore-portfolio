package com.finpulse.server.order.mapper;

import com.finpulse.server.order.domain.model.Order;
import com.finpulse.server.order.dto.TradeOrderRequest;
import com.finpulse.server.order.dto.TradeOrderResponse;
import org.springframework.stereotype.Component;

@Component
public class TradeOrderMapper {
  public Order toDomain(TradeOrderRequest request) {
    return Order.createOrder(
        request.getAccountId(),
        request.getInstrumentId(),
        request.getSide(),
        request.getQuantity(),
        request.getOrderType(),
        request.getStatus());
  }

  public void apply(TradeOrderRequest request, Order order) {
    order.updateOrder(
        request.getAccountId(),
        request.getInstrumentId(),
        request.getSide(),
        request.getQuantity(),
        request.getOrderType(),
        request.getStatus());
  }

  public TradeOrderResponse toResponse(Order order) {
    return TradeOrderResponse.builder()
        .orderId(order.getId().getValue())
        .accountId(order.getAccountId().getValue())
        .instrumentId(order.getInstrumentId().getValue())
        .side(order.getSide().name())
        .quantity(order.getQuantity().getValue())
        .orderType(order.getOrderType().name())
        .status(order.getStatus().name())
        .createdAt(order.getCreatedAt())
        .build();
  }
}
