package com.finpulse.server.payment.domain.model;

import com.finpulse.server.account.domain.model.AccountId;
import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
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
public class Payment extends AbstractEntity<PaymentId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "account_id", nullable = false))
  private AccountId accountId;

  private String counterparty;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "amount", nullable = false)),
    @AttributeOverride(
        name = "currency.code",
        column = @Column(name = "currency", nullable = false))
  })
  private Money amount;

  @NotNull
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  private Payment(
      PaymentId id, AccountId accountId, String counterparty, Money amount, PaymentStatus status) {
    super(id);
    this.accountId = Objects.requireNonNull(accountId, "accountId cannot be null");
    this.counterparty = counterparty;
    this.amount = Objects.requireNonNull(amount, "amount cannot be null");
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }

  public static Payment createPayment(
      UUID accountId, String counterparty, BigDecimal amount, String currency, String status) {
    return new Payment(
        PaymentId.generateId(),
        AccountId.parseId(accountId),
        counterparty,
        Money.createMoney(amount, currency),
        PaymentStatus.parseStatus(status));
  }

  public void updatePayment(
      UUID accountId, String counterparty, BigDecimal amount, String currency, String status) {
    this.accountId = AccountId.parseId(accountId);
    this.counterparty = counterparty;
    this.amount = Money.createMoney(amount, currency);
    this.status = PaymentStatus.parseStatus(status);
  }

  public void changeStatus(PaymentStatus status) {
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }
}
