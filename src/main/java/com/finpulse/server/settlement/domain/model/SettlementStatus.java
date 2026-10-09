package com.finpulse.server.settlement.domain.model;

public enum SettlementStatus {
  PENDING,
  SETTLED;

  public static SettlementStatus parseStatus(String text) {
    if (text == null || text.isBlank()) {
      return PENDING;
    }
    return SettlementStatus.valueOf(text.strip().toUpperCase());
  }
}
