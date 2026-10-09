package com.finpulse.server.common.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Amount with currency. */
@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public final class Money extends AbstractEmbeddable {

  @NotNull private BigDecimal amount;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "code", column = @Column(name = "currency"))
  private CurrencyCode currency;

  private Money(BigDecimal amount, CurrencyCode currency) {
    this.amount = Objects.requireNonNull(amount, "amount cannot be null");
    this.currency = Objects.requireNonNull(currency, "currency cannot be null");
  }

  public static Money createMoney(BigDecimal amount, CurrencyCode currency) {
    return new Money(amount, currency);
  }

  public static Money createMoney(BigDecimal amount, String currencyCode) {
    return createMoney(amount, CurrencyCode.parseCode(currencyCode));
  }
}
