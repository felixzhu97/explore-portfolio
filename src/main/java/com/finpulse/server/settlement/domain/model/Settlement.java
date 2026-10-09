package com.finpulse.server.settlement.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.payment.domain.model.PaymentId;
import com.finpulse.server.trade.domain.model.TradeId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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
public class Settlement extends AbstractEntity<SettlementId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "trade_id", nullable = false))
  private TradeId tradeId;

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "payment_id", nullable = false))
  private PaymentId paymentId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private SettlementStatus status;

  private Instant settledAt;

  private Settlement(
      SettlementId id,
      TradeId tradeId,
      PaymentId paymentId,
      SettlementStatus status,
      Instant settledAt) {
    super(id);
    this.tradeId = Objects.requireNonNull(tradeId, "tradeId cannot be null");
    this.paymentId = Objects.requireNonNull(paymentId, "paymentId cannot be null");
    this.status = Objects.requireNonNull(status, "status cannot be null");
    this.settledAt = settledAt;
  }

  public static Settlement createSettlement(
      UUID tradeId, UUID paymentId, String status, Instant settledAt) {
    return new Settlement(
        SettlementId.generateId(),
        TradeId.parseId(tradeId),
        PaymentId.parseId(paymentId),
        SettlementStatus.parseStatus(status),
        settledAt);
  }

  public void updateSettlement(
      UUID tradeId, UUID paymentId, String status, Instant settledAt) {
    this.tradeId = TradeId.parseId(tradeId);
    this.paymentId = PaymentId.parseId(paymentId);
    this.status = SettlementStatus.parseStatus(status);
    this.settledAt = settledAt;
  }

  public void markSettled(Instant settledAt) {
    this.status = SettlementStatus.SETTLED;
    this.settledAt = Objects.requireNonNull(settledAt, "settledAt cannot be null");
  }
}
