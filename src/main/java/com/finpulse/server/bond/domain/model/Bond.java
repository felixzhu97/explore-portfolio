package com.finpulse.server.bond.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class Bond extends AbstractEntity<BondId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "instrument_id", nullable = false))
  private InstrumentId instrumentId;

  private BigDecimal faceValue;

  private BigDecimal couponRate;

  private BigDecimal ytm;

  private BigDecimal duration;

  private BigDecimal convexity;

  private BigDecimal maturityYears;

  private Integer frequency;

  private Bond(
      BondId id,
      InstrumentId instrumentId,
      BigDecimal faceValue,
      BigDecimal couponRate,
      BigDecimal ytm,
      BigDecimal duration,
      BigDecimal convexity,
      BigDecimal maturityYears,
      Integer frequency) {
    super(id);
    this.instrumentId = Objects.requireNonNull(instrumentId, "instrumentId cannot be null");
    this.faceValue = faceValue;
    this.couponRate = couponRate;
    this.ytm = ytm;
    this.duration = duration;
    this.convexity = convexity;
    this.maturityYears = maturityYears;
    this.frequency = frequency;
  }

  public static Bond createBond(
      UUID instrumentId,
      BigDecimal faceValue,
      BigDecimal couponRate,
      BigDecimal ytm,
      BigDecimal duration,
      BigDecimal convexity,
      BigDecimal maturityYears,
      Integer frequency) {
    return new Bond(
        BondId.generateId(),
        InstrumentId.parseId(instrumentId),
        faceValue,
        couponRate,
        ytm,
        duration,
        convexity,
        maturityYears,
        frequency);
  }

  public void updateBond(
      UUID instrumentId,
      BigDecimal faceValue,
      BigDecimal couponRate,
      BigDecimal ytm,
      BigDecimal duration,
      BigDecimal convexity,
      BigDecimal maturityYears,
      Integer frequency) {
    this.instrumentId = InstrumentId.parseId(instrumentId);
    this.faceValue = faceValue;
    this.couponRate = couponRate;
    this.ytm = ytm;
    this.duration = duration;
    this.convexity = convexity;
    this.maturityYears = maturityYears;
    this.frequency = frequency;
  }
}
