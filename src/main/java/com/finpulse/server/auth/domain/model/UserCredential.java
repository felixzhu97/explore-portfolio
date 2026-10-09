package com.finpulse.server.auth.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.customer.domain.model.CustomerId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class UserCredential extends AbstractEntity<CredentialId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "customer_id", nullable = false))
  private CustomerId customerId;

  @NotBlank private String email;

  @NotBlank private String passwordHash;

  private UserCredential(
      CredentialId id, CustomerId customerId, String email, String passwordHash) {
    super(id);
    this.customerId = Objects.requireNonNull(customerId, "customerId cannot be null");
    this.email = Objects.requireNonNull(email, "email cannot be null");
    this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash cannot be null");
  }

  public static UserCredential createCredential(
      UUID customerId, String email, String passwordHash) {
    return new UserCredential(
        CredentialId.generateId(), CustomerId.parseId(customerId), email, passwordHash);
  }

  public void updatePasswordHash(String passwordHash) {
    this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash cannot be null");
  }
}
