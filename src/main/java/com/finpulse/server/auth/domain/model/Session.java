package com.finpulse.server.auth.domain.model;

import com.finpulse.server.common.domain.model.AbstractImmutable;
import com.finpulse.server.customer.domain.model.CustomerId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Session extends AbstractImmutable<SessionId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "customer_id", nullable = false))
  private CustomerId customerId;

  @NotBlank private String token;

  @NotNull private Instant expiresAt;

  private Session(SessionId id, CustomerId customerId, String token, Instant expiresAt) {
    super(id);
    this.customerId = Objects.requireNonNull(customerId, "customerId cannot be null");
    this.token = Objects.requireNonNull(token, "token cannot be null");
    this.expiresAt = Objects.requireNonNull(expiresAt, "expiresAt cannot be null");
  }

  public static Session createSession(UUID customerId, String token, Instant expiresAt) {
    return new Session(
        SessionId.generateId(), CustomerId.parseId(customerId), token, expiresAt);
  }

  public boolean isExpired() {
    return Instant.now().isAfter(expiresAt);
  }
}
