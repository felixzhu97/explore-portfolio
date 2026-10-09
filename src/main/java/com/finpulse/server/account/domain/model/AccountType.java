package com.finpulse.server.account.domain.model;

public enum AccountType {
  CASH,
  MARGIN,
  BROKERAGE;

  public static AccountType parseType(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("accountType cannot be blank");
    }
    return AccountType.valueOf(text.strip().toUpperCase());
  }
}
