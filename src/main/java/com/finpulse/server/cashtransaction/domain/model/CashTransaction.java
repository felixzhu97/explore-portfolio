package com.finpulse.server.cashtransaction.domain.model;

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
public class CashTransaction extends AbstractEntity<CashTransactionId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "account_id", nullable = false))
  private AccountId accountId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private CashTxnType type;

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
  private CashTxnStatus status;

  private CashTransaction(
      CashTransactionId id,
      AccountId accountId,
      CashTxnType type,
      Money amount,
      CashTxnStatus status) {
    super(id);
    this.accountId = Objects.requireNonNull(accountId, "accountId cannot be null");
    this.type = Objects.requireNonNull(type, "type cannot be null");
    this.amount = Objects.requireNonNull(amount, "amount cannot be null");
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }

  public static CashTransaction createCashTransaction(
      UUID accountId, String type, BigDecimal amount, String currency, String status) {
    return new CashTransaction(
        CashTransactionId.generateId(),
        AccountId.parseId(accountId),
        CashTxnType.parseType(type),
        Money.createMoney(amount, currency),
        CashTxnStatus.parseStatus(status));
  }

  public void updateCashTransaction(
      UUID accountId, String type, BigDecimal amount, String currency, String status) {
    this.accountId = AccountId.parseId(accountId);
    this.type = CashTxnType.parseType(type);
    this.amount = Money.createMoney(amount, currency);
    this.status = CashTxnStatus.parseStatus(status);
  }

  public void changeStatus(CashTxnStatus status) {
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }
}
