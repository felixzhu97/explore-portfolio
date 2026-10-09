package com.finpulse.server.option.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Option extends AbstractEntity<OptionId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "instrument_id", nullable = false))
  private InstrumentId instrumentId;

  @NotNull
  @Embedded
  @AttributeOverride(
      name = "value",
      column = @Column(name = "underlying_instrument_id", nullable = false))
  private InstrumentId underlyingInstrumentId;

  @NotNull private BigDecimal strike;

  @NotNull private Instant expiry;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OptionType optionType;

  private BigDecimal riskFreeRate;

  private BigDecimal volatility;

  private BigDecimal bsPrice;

  private BigDecimal delta;

  private BigDecimal gamma;

  private BigDecimal theta;

  private BigDecimal vega;

  private BigDecimal rho;

  private BigDecimal impliedVolatility;

  private Option(
      OptionId id,
      InstrumentId instrumentId,
      InstrumentId underlyingInstrumentId,
      BigDecimal strike,
      Instant expiry,
      OptionType optionType,
      BigDecimal riskFreeRate,
      BigDecimal volatility,
      BigDecimal bsPrice,
      BigDecimal delta,
      BigDecimal gamma,
      BigDecimal theta,
      BigDecimal vega,
      BigDecimal rho,
      BigDecimal impliedVolatility) {
    super(id);
    this.instrumentId = Objects.requireNonNull(instrumentId, "instrumentId cannot be null");
    this.underlyingInstrumentId =
        Objects.requireNonNull(underlyingInstrumentId, "underlyingInstrumentId cannot be null");
    this.strike = Objects.requireNonNull(strike, "strike cannot be null");
    this.expiry = Objects.requireNonNull(expiry, "expiry cannot be null");
    this.optionType = Objects.requireNonNull(optionType, "optionType cannot be null");
    this.riskFreeRate = riskFreeRate;
    this.volatility = volatility;
    this.bsPrice = bsPrice;
    this.delta = delta;
    this.gamma = gamma;
    this.theta = theta;
    this.vega = vega;
    this.rho = rho;
    this.impliedVolatility = impliedVolatility;
  }

  public static Option createOption(
      UUID instrumentId,
      UUID underlyingInstrumentId,
      BigDecimal strike,
      Instant expiry,
      String optionType,
      BigDecimal riskFreeRate,
      BigDecimal volatility,
      BigDecimal bsPrice,
      BigDecimal delta,
      BigDecimal gamma,
      BigDecimal theta,
      BigDecimal vega,
      BigDecimal rho,
      BigDecimal impliedVolatility) {
    return new Option(
        OptionId.generateId(),
        InstrumentId.parseId(instrumentId),
        InstrumentId.parseId(underlyingInstrumentId),
        strike,
        expiry,
        OptionType.parseType(optionType),
        riskFreeRate,
        volatility,
        bsPrice,
        delta,
        gamma,
        theta,
        vega,
        rho,
        impliedVolatility);
  }

  public void updateOption(
      UUID instrumentId,
      UUID underlyingInstrumentId,
      BigDecimal strike,
      Instant expiry,
      String optionType,
      BigDecimal riskFreeRate,
      BigDecimal volatility,
      BigDecimal bsPrice,
      BigDecimal delta,
      BigDecimal gamma,
      BigDecimal theta,
      BigDecimal vega,
      BigDecimal rho,
      BigDecimal impliedVolatility) {
    this.instrumentId = InstrumentId.parseId(instrumentId);
    this.underlyingInstrumentId = InstrumentId.parseId(underlyingInstrumentId);
    this.strike = Objects.requireNonNull(strike, "strike cannot be null");
    this.expiry = Objects.requireNonNull(expiry, "expiry cannot be null");
    this.optionType = OptionType.parseType(optionType);
    this.riskFreeRate = riskFreeRate;
    this.volatility = volatility;
    this.bsPrice = bsPrice;
    this.delta = delta;
    this.gamma = gamma;
    this.theta = theta;
    this.vega = vega;
    this.rho = rho;
    this.impliedVolatility = impliedVolatility;
  }
}
