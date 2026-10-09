package com.finpulse.server.common.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Decimal holding or order quantity. */
@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public final class Quantity extends AbstractEmbeddable {

  @NotNull private BigDecimal value;

  private Quantity(BigDecimal value) {
    this.value = Objects.requireNonNull(value, "value cannot be null");
  }

  public static Quantity createQuantity(BigDecimal value) {
    return new Quantity(value);
  }

  @Override
  public String toString() {
    return value.toPlainString();
  }
}
