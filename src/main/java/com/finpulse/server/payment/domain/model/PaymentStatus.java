package com.finpulse.server.payment.domain.model;

public enum PaymentStatus {
  PENDING,
  COMPLETED,
  FAILED;

  public static PaymentStatus parseStatus(String text) {
    if (text == null || text.isBlank()) {
      return PENDING;
    }
    return PaymentStatus.valueOf(text.strip().toUpperCase());
  }
}
