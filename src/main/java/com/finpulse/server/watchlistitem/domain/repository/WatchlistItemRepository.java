package com.finpulse.server.watchlistitem.domain.repository;

import com.finpulse.server.watchlistitem.domain.model.WatchlistItem;
import com.finpulse.server.watchlistitem.domain.model.WatchlistItemId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface WatchlistItemRepository extends Repository<WatchlistItem, WatchlistItemId> {

  List<WatchlistItem> findAllByOrderByCreatedAtDesc();

  Optional<WatchlistItem> findById(WatchlistItemId id);

  boolean existsById(WatchlistItemId id);

  WatchlistItem save(WatchlistItem watchlistItem);

  void deleteById(WatchlistItemId id);
}
