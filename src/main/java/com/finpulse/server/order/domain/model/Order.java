package com.finpulse.server.order.domain.model;

import com.finpulse.server.account.domain.model.AccountId;
import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.Quantity;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
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
public class Order extends AbstractEntity<OrderId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "account_id", nullable = false))
  private AccountId accountId;

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "instrument_id", nullable = false))
  private InstrumentId instrumentId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OrderSide side;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = false))
  private Quantity quantity;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OrderType orderType;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private Order(
      OrderId id,
      AccountId accountId,
      InstrumentId instrumentId,
      OrderSide side,
      Quantity quantity,
      OrderType orderType,
      OrderStatus status) {
    super(id);
    this.accountId = Objects.requireNonNull(accountId, "accountId cannot be null");
    this.instrumentId = Objects.requireNonNull(instrumentId, "instrumentId cannot be null");
    this.side = Objects.requireNonNull(side, "side cannot be null");
    this.quantity = Objects.requireNonNull(quantity, "quantity cannot be null");
    this.orderType = Objects.requireNonNull(orderType, "orderType cannot be null");
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }

  public static Order createOrder(
      UUID accountId,
      UUID instrumentId,
      String side,
      BigDecimal quantity,
      String orderType,
      String status) {
    return new Order(
        OrderId.generateId(),
        AccountId.parseId(accountId),
        InstrumentId.parseId(instrumentId),
        OrderSide.parseSide(side),
        Quantity.createQuantity(quantity),
        OrderType.parseType(orderType),
        OrderStatus.parseStatus(status));
  }

  public void updateOrder(
      UUID accountId,
      UUID instrumentId,
      String side,
      BigDecimal quantity,
      String orderType,
      String status) {
    this.accountId = AccountId.parseId(accountId);
    this.instrumentId = InstrumentId.parseId(instrumentId);
    this.side = OrderSide.parseSide(side);
    this.quantity = Quantity.createQuantity(quantity);
    this.orderType = OrderType.parseType(orderType);
    this.status = OrderStatus.parseStatus(status);
  }

  public void changeStatus(OrderStatus status) {
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }
}
