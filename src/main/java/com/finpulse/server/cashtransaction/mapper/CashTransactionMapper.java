package com.finpulse.server.cashtransaction.mapper;

import com.finpulse.server.cashtransaction.domain.model.CashTransaction;
import com.finpulse.server.cashtransaction.dto.CashTransactionRequest;
import com.finpulse.server.cashtransaction.dto.CashTransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class CashTransactionMapper {
  public CashTransaction toDomain(CashTransactionRequest request) {
    return CashTransaction.createCashTransaction(
        request.getAccountId(),
        request.getType(),
        request.getAmount(),
        request.getCurrency(),
        request.getStatus());
  }

  public void apply(CashTransactionRequest request, CashTransaction cashTransaction) {
    cashTransaction.updateCashTransaction(
        request.getAccountId(),
        request.getType(),
        request.getAmount(),
        request.getCurrency(),
        request.getStatus());
  }

  public CashTransactionResponse toResponse(CashTransaction cashTransaction) {
    return CashTransactionResponse.builder()
        .transactionId(cashTransaction.getId().getValue())
        .accountId(cashTransaction.getAccountId().getValue())
        .type(cashTransaction.getType().name())
        .amount(cashTransaction.getAmount().getAmount())
        .currency(cashTransaction.getAmount().getCurrency().getCode())
        .status(cashTransaction.getStatus().name())
        .createdAt(cashTransaction.getCreatedAt())
        .build();
  }
}
