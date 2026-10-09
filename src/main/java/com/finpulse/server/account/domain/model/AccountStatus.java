package com.finpulse.server.account.domain.model;

public enum AccountStatus {
  ACTIVE,
  CLOSED,
  SUSPENDED;

  public static AccountStatus parseStatus(String text) {
    if (text == null || text.isBlank()) {
      return ACTIVE;
    }
    return AccountStatus.valueOf(text.strip().toUpperCase());
  }
}
