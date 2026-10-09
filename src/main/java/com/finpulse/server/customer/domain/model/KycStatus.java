package com.finpulse.server.customer.domain.model;

public enum KycStatus {
  PENDING,
  VERIFIED,
  REJECTED;

  public static KycStatus parseStatus(String text) {
    if (text == null || text.isBlank()) {
      return null;
    }
    return KycStatus.valueOf(text.strip().toUpperCase());
  }
}
