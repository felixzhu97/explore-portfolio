package com.finpulse.server.option.domain.model;

public enum OptionType {
  CALL,
  PUT;

  public static OptionType parseType(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("optionType cannot be blank");
    }
    return OptionType.valueOf(text.strip().toUpperCase());
  }
}
