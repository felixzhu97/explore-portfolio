package com.finpulse.server.instrument.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.CurrencyCode;
import com.finpulse.server.common.domain.model.Symbol;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Instrument extends AbstractEntity<InstrumentId> {

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "symbol", nullable = false))
  private Symbol symbol;

  private String name;

  @Enumerated(EnumType.STRING)
  private AssetClass assetClass;

  @Valid
  @Embedded
  @AttributeOverride(name = "code", column = @Column(name = "currency"))
  private CurrencyCode currency;

  private String exchange;

  private Instrument(
      InstrumentId id,
      Symbol symbol,
      String name,
      AssetClass assetClass,
      CurrencyCode currency,
      String exchange) {
    super(id);
    this.symbol = Objects.requireNonNull(symbol, "symbol cannot be null");
    this.name = name;
    this.assetClass = assetClass;
    this.currency = currency;
    this.exchange = exchange;
  }

  public static Instrument createInstrument(
      String symbol, String name, String assetClass, String currency, String exchange) {
    return new Instrument(
        InstrumentId.generateId(),
        Symbol.createSymbol(symbol),
        name,
        AssetClass.parseClass(assetClass),
        parseCurrency(currency),
        exchange);
  }

  public void updateInstrument(
      String symbol, String name, String assetClass, String currency, String exchange) {
    this.symbol = Symbol.createSymbol(symbol);
    this.name = name;
    this.assetClass = AssetClass.parseClass(assetClass);
    this.currency = parseCurrency(currency);
    this.exchange = exchange;
  }

  private static CurrencyCode parseCurrency(String currency) {
    if (currency == null || currency.isBlank()) {
      return null;
    }
    return CurrencyCode.parseCode(currency);
  }
}
