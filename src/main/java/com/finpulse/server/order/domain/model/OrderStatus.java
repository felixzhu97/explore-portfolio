package com.finpulse.server.order.domain.model;

public enum OrderStatus {
  PENDING,
  FILLED,
  CANCELLED;

  public static OrderStatus parseStatus(String text) {
    if (text == null || text.isBlank()) {
      return PENDING;
    }
    return OrderStatus.valueOf(text.strip().toUpperCase());
  }
}
