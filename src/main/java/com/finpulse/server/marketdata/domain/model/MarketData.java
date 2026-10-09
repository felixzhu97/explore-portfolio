package com.finpulse.server.marketdata.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class MarketData extends AbstractEntity<MarketDataId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "instrument_id", nullable = false))
  private InstrumentId instrumentId;

  @NotNull private Instant timestamp;

  private BigDecimal open;

  private BigDecimal high;

  private BigDecimal low;

  @NotNull private BigDecimal close;

  private BigDecimal volume;

  private BigDecimal changePct;

  private MarketData(
      MarketDataId id,
      InstrumentId instrumentId,
      Instant timestamp,
      BigDecimal open,
      BigDecimal high,
      BigDecimal low,
      BigDecimal close,
      BigDecimal volume,
      BigDecimal changePct) {
    super(id);
    this.instrumentId = Objects.requireNonNull(instrumentId, "instrumentId cannot be null");
    this.timestamp = Objects.requireNonNull(timestamp, "timestamp cannot be null");
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = Objects.requireNonNull(close, "close cannot be null");
    this.volume = volume;
    this.changePct = changePct;
  }

  public static MarketData createMarketData(
      UUID instrumentId,
      Instant timestamp,
      BigDecimal open,
      BigDecimal high,
      BigDecimal low,
      BigDecimal close,
      BigDecimal volume,
      BigDecimal changePct) {
    return new MarketData(
        MarketDataId.generateId(),
        InstrumentId.parseId(instrumentId),
        timestamp,
        open,
        high,
        low,
        close,
        volume,
        changePct);
  }

  public void updateMarketData(
      UUID instrumentId,
      Instant timestamp,
      BigDecimal open,
      BigDecimal high,
      BigDecimal low,
      BigDecimal close,
      BigDecimal volume,
      BigDecimal changePct) {
    this.instrumentId = InstrumentId.parseId(instrumentId);
    this.timestamp = Objects.requireNonNull(timestamp, "timestamp cannot be null");
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = Objects.requireNonNull(close, "close cannot be null");
    this.volume = volume;
    this.changePct = changePct;
  }
}
