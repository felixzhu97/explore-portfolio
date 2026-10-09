package com.finpulse.server.watchlistitem.domain.model;

import com.finpulse.server.common.domain.model.AbstractImmutable;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import com.finpulse.server.watchlist.domain.model.WatchlistId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class WatchlistItem extends AbstractImmutable<WatchlistItemId> {

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "watchlist_id", nullable = false))
  private WatchlistId watchlistId;

  @NotNull
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "instrument_id", nullable = false))
  private InstrumentId instrumentId;

  @NotNull private Instant addedAt;

  private WatchlistItem(
      WatchlistItemId id, WatchlistId watchlistId, InstrumentId instrumentId, Instant addedAt) {
    super(id);
    this.watchlistId = Objects.requireNonNull(watchlistId, "watchlistId cannot be null");
    this.instrumentId = Objects.requireNonNull(instrumentId, "instrumentId cannot be null");
    this.addedAt = Objects.requireNonNull(addedAt, "addedAt cannot be null");
  }

  public static WatchlistItem createWatchlistItem(UUID watchlistId, UUID instrumentId) {
    return new WatchlistItem(
        WatchlistItemId.generateId(),
        WatchlistId.parseId(watchlistId),
        InstrumentId.parseId(instrumentId),
        Instant.now());
  }

  public void updateWatchlistItem(UUID watchlistId, UUID instrumentId) {
    this.watchlistId = WatchlistId.parseId(watchlistId);
    this.instrumentId = InstrumentId.parseId(instrumentId);
  }
}
