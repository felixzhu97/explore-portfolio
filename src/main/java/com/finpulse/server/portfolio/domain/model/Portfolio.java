package com.finpulse.server.portfolio.domain.model;

import com.finpulse.server.account.domain.model.AccountId;
import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.common.domain.model.CurrencyCode;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Portfolio extends AbstractEntity<PortfolioId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "account_id", nullable = false))
  private AccountId accountId;

  @NotBlank private String name;

  @NotNull
  @Valid
  @Embedded
  @AttributeOverride(name = "code", column = @Column(name = "base_currency", nullable = false))
  private CurrencyCode baseCurrency;

  private Portfolio(PortfolioId id, AccountId accountId, String name, CurrencyCode baseCurrency) {
    super(id);
    this.accountId = Objects.requireNonNull(accountId, "accountId cannot be null");
    this.name = Objects.requireNonNull(name, "name cannot be null");
    this.baseCurrency = Objects.requireNonNull(baseCurrency, "baseCurrency cannot be null");
  }

  public static Portfolio createPortfolio(UUID accountId, String name, String baseCurrency) {
    return new Portfolio(
        PortfolioId.generateId(),
        AccountId.parseId(accountId),
        name,
        CurrencyCode.parseCode(baseCurrency));
  }

  public void updatePortfolio(UUID accountId, String name, String baseCurrency) {
    this.accountId = AccountId.parseId(accountId);
    this.name = Objects.requireNonNull(name, "name cannot be null");
    this.baseCurrency = CurrencyCode.parseCode(baseCurrency);
  }

  public void updateName(String name) {
    this.name = Objects.requireNonNull(name, "name cannot be null");
  }

  public void updateBaseCurrency(String baseCurrency) {
    this.baseCurrency = CurrencyCode.parseCode(baseCurrency);
  }
}
