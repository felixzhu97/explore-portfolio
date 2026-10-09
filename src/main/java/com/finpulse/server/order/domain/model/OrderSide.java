package com.finpulse.server.order.domain.model;

public enum OrderSide {
  BUY,
  SELL;

  public static OrderSide parseSide(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("side cannot be blank");
    }
    return OrderSide.valueOf(text.strip().toUpperCase());
  }
}
