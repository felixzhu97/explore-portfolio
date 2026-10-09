package com.finpulse.server.account.mapper;

import com.finpulse.server.account.domain.model.Account;
import com.finpulse.server.account.dto.AccountRequest;
import com.finpulse.server.account.dto.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
  public Account toDomain(AccountRequest request) {
    return Account.createAccount(
        request.getCustomerId(),
        request.getAccountType(),
        request.getCurrency(),
        request.getStatus());
  }

  public void apply(AccountRequest request, Account account) {
    String status =
        request.getStatus() == null || request.getStatus().isBlank()
            ? account.getStatus().name()
            : request.getStatus();
    account.updateAccount(
        request.getCustomerId(), request.getAccountType(), request.getCurrency(), status);
  }

  public AccountResponse toResponse(Account account) {
    return AccountResponse.builder()
        .accountId(account.getId().getValue())
        .customerId(account.getCustomerId().getValue())
        .accountType(account.getAccountType().name())
        .currency(account.getCurrency().getCode())
        .status(account.getStatus().name())
        .openedAt(account.getOpenedAt())
        .build();
  }
}
