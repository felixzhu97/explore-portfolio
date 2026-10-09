package com.finpulse.server.trade.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.Quantity;
import com.finpulse.server.order.domain.model.OrderId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
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
public class Trade extends AbstractEntity<TradeId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "order_id", nullable = false))
  private OrderId orderId;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = false))
  private Quantity quantity;

  @NotNull private BigDecimal price;

  private BigDecimal fee;

  @NotNull private Instant executedAt;

  private Trade(
      TradeId id,
      OrderId orderId,
      Quantity quantity,
      BigDecimal price,
      BigDecimal fee,
      Instant executedAt) {
    super(id);
    this.orderId = Objects.requireNonNull(orderId, "orderId cannot be null");
    this.quantity = Objects.requireNonNull(quantity, "quantity cannot be null");
    this.price = Objects.requireNonNull(price, "price cannot be null");
    this.fee = fee;
    this.executedAt = Objects.requireNonNull(executedAt, "executedAt cannot be null");
  }

  public static Trade createTrade(
      UUID orderId, BigDecimal quantity, BigDecimal price, BigDecimal fee) {
    return new Trade(
        TradeId.generateId(),
        OrderId.parseId(orderId),
        Quantity.createQuantity(quantity),
        price,
        fee,
        Instant.now());
  }

  public void updateTrade(UUID orderId, BigDecimal quantity, BigDecimal price, BigDecimal fee) {
    this.orderId = OrderId.parseId(orderId);
    this.quantity = Quantity.createQuantity(quantity);
    this.price = Objects.requireNonNull(price, "price cannot be null");
    this.fee = fee;
  }
}
