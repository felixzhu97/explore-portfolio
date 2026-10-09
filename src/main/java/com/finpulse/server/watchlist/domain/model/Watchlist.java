package com.finpulse.server.watchlist.domain.model;

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
public class Watchlist extends AbstractEntity<WatchlistId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "customer_id", nullable = false))
  private CustomerId customerId;

  @NotBlank private String name;

  private Watchlist(WatchlistId id, CustomerId customerId, String name) {
    super(id);
    this.customerId = Objects.requireNonNull(customerId, "customerId cannot be null");
    this.name = Objects.requireNonNull(name, "name cannot be null");
  }

  public static Watchlist createWatchlist(UUID customerId, String name) {
    return new Watchlist(WatchlistId.generateId(), CustomerId.parseId(customerId), name);
  }

  public void updateName(String name) {
    this.name = Objects.requireNonNull(name, "name cannot be null");
  }

  public void updateWatchlist(UUID customerId, String name) {
    this.customerId = CustomerId.parseId(customerId);
    updateName(name);
  }
}
