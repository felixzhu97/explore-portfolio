package com.finpulse.server.common.domain.model;

import jakarta.persistence.Embeddable;
import java.util.Locale;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/** ISO-style currency code embedded in money and account fields. */
@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public final class CurrencyCode extends AbstractEmbeddable {

  @NonNull private String code;

  private CurrencyCode(String code) {
    this.code = Objects.requireNonNull(code, "code cannot be null").strip().toUpperCase(Locale.ROOT);
    if (this.code.isEmpty()) {
      throw new IllegalArgumentException("code cannot be blank");
    }
  }

  public static CurrencyCode parseCode(String code) {
    return new CurrencyCode(code);
  }

  @Override
  public String toString() {
    return code;
  }
}
