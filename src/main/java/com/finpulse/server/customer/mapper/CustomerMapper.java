package com.finpulse.server.customer.mapper;

import com.finpulse.server.customer.domain.model.Customer;
import com.finpulse.server.customer.domain.model.KycStatus;
import com.finpulse.server.customer.dto.CustomerRequest;
import com.finpulse.server.customer.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
  public Customer toDomain(CustomerRequest request) {
    return Customer.createCustomer(request.getName(), request.getEmail(), request.getKycStatus());
  }

  public void apply(CustomerRequest request, Customer customer) {
    customer.updateProfile(request.getName(), request.getEmail(), request.getKycStatus());
  }

  public CustomerResponse toResponse(Customer customer) {
    KycStatus status = customer.getKycStatus();
    return CustomerResponse.builder()
        .customerId(customer.getId().getValue())
        .name(customer.getName())
        .email(customer.getEmail())
        .kycStatus(status == null ? null : status.name())
        .createdAt(customer.getCreatedAt())
        .build();
  }
}
