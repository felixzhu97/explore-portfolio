package com.finpulse.server.payment.mapper;

import com.finpulse.server.payment.domain.model.Payment;
import com.finpulse.server.payment.dto.PaymentRequest;
import com.finpulse.server.payment.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
  public Payment toDomain(PaymentRequest request) {
    return Payment.createPayment(
        request.getAccountId(),
        request.getCounterparty(),
        request.getAmount(),
        request.getCurrency(),
        request.getStatus());
  }

  public void apply(PaymentRequest request, Payment payment) {
    payment.updatePayment(
        request.getAccountId(),
        request.getCounterparty(),
        request.getAmount(),
        request.getCurrency(),
        request.getStatus());
  }

  public PaymentResponse toResponse(Payment payment) {
    return PaymentResponse.builder()
        .paymentId(payment.getId().getValue())
        .accountId(payment.getAccountId().getValue())
        .counterparty(payment.getCounterparty())
        .amount(payment.getAmount().getAmount())
        .currency(payment.getAmount().getCurrency().getCode())
        .status(payment.getStatus().name())
        .createdAt(payment.getCreatedAt())
        .build();
  }
}
