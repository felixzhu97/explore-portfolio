package com.finpulse.server.instrument.domain.model;

public enum AssetClass {
  EQUITY,
  BOND,
  OPTION,
  OTHER;

  public static AssetClass parseClass(String text) {
    if (text == null || text.isBlank()) {
      return null;
    }
    return AssetClass.valueOf(text.strip().toUpperCase());
  }
}
