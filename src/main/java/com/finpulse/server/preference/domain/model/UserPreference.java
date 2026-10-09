package com.finpulse.server.preference.domain.model;

import com.finpulse.server.common.domain.model.AbstractEntity;
import com.finpulse.server.customer.domain.model.CustomerId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class UserPreference extends AbstractEntity<PreferenceId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "customer_id", nullable = false))
  private CustomerId customerId;

  private String theme;

  private String language;

  private boolean notificationsEnabled;

  private UserPreference(
      PreferenceId id,
      CustomerId customerId,
      String theme,
      String language,
      boolean notificationsEnabled) {
    super(id);
    this.customerId = Objects.requireNonNull(customerId, "customerId cannot be null");
    this.theme = theme;
    this.language = language;
    this.notificationsEnabled = notificationsEnabled;
  }

  public static UserPreference createPreference(
      UUID customerId, String theme, String language, boolean notificationsEnabled) {
    return new UserPreference(
        PreferenceId.generateId(),
        CustomerId.parseId(customerId),
        theme,
        language,
        notificationsEnabled);
  }

  public void updatePreference(
      UUID customerId, String theme, String language, boolean notificationsEnabled) {
    this.customerId = CustomerId.parseId(customerId);
    this.theme = theme;
    this.language = language;
    this.notificationsEnabled = notificationsEnabled;
  }
}
