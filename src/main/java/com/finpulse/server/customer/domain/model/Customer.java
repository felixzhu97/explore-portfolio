package com.finpulse.server.customer.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Customer extends AbstractEntity<CustomerId> {

  @NotBlank private String name;

  private String email;

  @Enumerated(EnumType.STRING)
  private KycStatus kycStatus;

  private Customer(CustomerId id, String name, String email, KycStatus kycStatus) {
    super(id);
    this.name = Objects.requireNonNull(name, "name cannot be null");
    this.email = email;
    this.kycStatus = kycStatus;
  }

  public static Customer createCustomer(String name, String email) {
    return createCustomer(name, email, null);
  }

  public static Customer createCustomer(String name, String email, String kycStatus) {
    return new Customer(
        CustomerId.generateId(), name, email, KycStatus.parseStatus(kycStatus));
  }

  public void updateProfile(String name, String email, String kycStatus) {
    this.name = Objects.requireNonNull(name, "name cannot be null");
    this.email = email;
    this.kycStatus = KycStatus.parseStatus(kycStatus);
  }
}
