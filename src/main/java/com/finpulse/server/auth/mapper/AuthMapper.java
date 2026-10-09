package com.finpulse.server.auth.mapper;

import com.finpulse.server.auth.dto.CustomerResponse;
import com.finpulse.server.auth.dto.LoginResponse;
import com.finpulse.server.customer.domain.model.Customer;
import com.finpulse.server.customer.domain.model.KycStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
  public CustomerResponse toCustomerResponse(Customer customer) {
    KycStatus status = customer.getKycStatus();
    return CustomerResponse.builder()
        .customerId(customer.getId().getValue())
        .name(customer.getName())
        .email(customer.getEmail())
        .kycStatus(status == null ? null : status.name())
        .createdAt(customer.getCreatedAt())
        .build();
  }

  public LoginResponse toLoginResponse(String token, Customer customer) {
    return LoginResponse.builder().token(token).customer(toCustomerResponse(customer)).build();
  }
}
