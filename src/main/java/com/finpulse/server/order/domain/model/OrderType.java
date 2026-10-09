package com.finpulse.server.order.domain.model;

public enum OrderType {
  MARKET,
  LIMIT;

  public static OrderType parseType(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("orderType cannot be blank");
    }
    return OrderType.valueOf(text.strip().toUpperCase());
  }
}
