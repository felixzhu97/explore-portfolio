package com.finpulse.server.position.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.Quantity;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import com.finpulse.server.portfolio.domain.model.PortfolioId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Position extends AbstractEntity<PositionId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "portfolio_id", nullable = false))
  private PortfolioId portfolioId;

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "instrument_id", nullable = false))
  private InstrumentId instrumentId;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = false))
  private Quantity quantity;

  private BigDecimal costBasis;

  @NotNull private LocalDate asOfDate;

  private Position(
      PositionId id,
      PortfolioId portfolioId,
      InstrumentId instrumentId,
      Quantity quantity,
      BigDecimal costBasis,
      LocalDate asOfDate) {
    super(id);
    this.portfolioId = Objects.requireNonNull(portfolioId, "portfolioId cannot be null");
    this.instrumentId = Objects.requireNonNull(instrumentId, "instrumentId cannot be null");
    this.quantity = Objects.requireNonNull(quantity, "quantity cannot be null");
    this.costBasis = costBasis;
    this.asOfDate = Objects.requireNonNull(asOfDate, "asOfDate cannot be null");
  }

  public static Position createPosition(
      UUID portfolioId, UUID instrumentId, BigDecimal quantity, BigDecimal costBasis) {
    return new Position(
        PositionId.generateId(),
        PortfolioId.parseId(portfolioId),
        InstrumentId.parseId(instrumentId),
        Quantity.createQuantity(quantity),
        costBasis,
        LocalDate.now());
  }

  public void updateQuantity(BigDecimal quantity) {
    this.quantity = Quantity.createQuantity(quantity);
  }

  public void updateCostBasis(BigDecimal costBasis) {
    this.costBasis = costBasis;
  }

  public void updatePosition(
      UUID portfolioId, UUID instrumentId, BigDecimal quantity, BigDecimal costBasis) {
    this.portfolioId = PortfolioId.parseId(portfolioId);
    this.instrumentId = InstrumentId.parseId(instrumentId);
    updateQuantity(quantity);
    updateCostBasis(costBasis);
  }
}
