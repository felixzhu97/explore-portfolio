package com.finpulse.server.cashtransaction.domain.model;

public enum CashTxnStatus {
  COMPLETED,
  PENDING;

  public static CashTxnStatus parseStatus(String text) {
    if (text == null || text.isBlank()) {
      return COMPLETED;
    }
    return CashTxnStatus.valueOf(text.strip().toUpperCase());
  }
}
