package com.finpulse.server.common.domain.model;

import jakarta.persistence.Embeddable;
import java.util.Locale;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/** Exchange ticker / instrument symbol. */
@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public final class Symbol extends AbstractEmbeddable {

  @NonNull private String value;

  private Symbol(String value) {
    this.value = Objects.requireNonNull(value, "value cannot be null").strip().toUpperCase(Locale.ROOT);
    if (this.value.isEmpty()) {
      throw new IllegalArgumentException("value cannot be blank");
    }
  }

  public static Symbol createSymbol(String value) {
    return new Symbol(value);
  }

  @Override
  public String toString() {
    return value;
  }
}
