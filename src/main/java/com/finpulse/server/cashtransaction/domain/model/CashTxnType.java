package com.finpulse.server.cashtransaction.domain.model;

public enum CashTxnType {
  DEPOSIT,
  WITHDRAWAL,
  FEE;

  public static CashTxnType parseType(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("type cannot be blank");
    }
    return CashTxnType.valueOf(text.strip().toUpperCase());
  }
}
