package com.finpulse.server.account.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.CurrencyCode;
import com.finpulse.server.customer.domain.model.CustomerId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
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
public class Account extends AbstractEntity<AccountId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "customer_id", nullable = false))
  private CustomerId customerId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "code", column = @Column(name = "currency", nullable = false))
  private CurrencyCode currency;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @NotNull private Instant openedAt;

  private Account(
      AccountId id,
      CustomerId customerId,
      AccountType accountType,
      CurrencyCode currency,
      AccountStatus status,
      Instant openedAt) {
    super(id);
    this.customerId = Objects.requireNonNull(customerId, "customerId cannot be null");
    this.accountType = Objects.requireNonNull(accountType, "accountType cannot be null");
    this.currency = Objects.requireNonNull(currency, "currency cannot be null");
    this.status = Objects.requireNonNull(status, "status cannot be null");
    this.openedAt = Objects.requireNonNull(openedAt, "openedAt cannot be null");
  }

  public static Account createAccount(
      UUID customerId, String accountType, String currency, String status) {
    return new Account(
        AccountId.generateId(),
        CustomerId.parseId(customerId),
        AccountType.parseType(accountType),
        CurrencyCode.parseCode(currency),
        AccountStatus.parseStatus(status),
        Instant.now());
  }

  public void updateAccount(
      UUID customerId, String accountType, String currency, String status) {
    this.customerId = CustomerId.parseId(customerId);
    this.accountType = AccountType.parseType(accountType);
    this.currency = CurrencyCode.parseCode(currency);
    this.status = AccountStatus.parseStatus(status);
  }

  public void changeStatus(AccountStatus status) {
    this.status = Objects.requireNonNull(status, "status cannot be null");
  }
}
