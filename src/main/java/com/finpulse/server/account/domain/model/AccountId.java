package com.finpulse.server.account.domain.model;

import com.finpulse.server.common.domain.model.AbstractEmbeddable;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(staticName = "createId")
public final class AccountId extends AbstractEmbeddable {

  @NonNull private UUID value;

  public static AccountId parseId(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("Id cannot be blank");
    }
    return createId(UUID.fromString(text.strip()));
  }

  public static AccountId parseId(UUID value) {
    return createId(value);
  }

  public static AccountId generateId() {
    return createId(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
