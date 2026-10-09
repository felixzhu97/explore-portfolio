package com.finpulse.server.watchlist.domain.repository;

import com.finpulse.server.watchlist.domain.model.Watchlist;
import com.finpulse.server.watchlist.domain.model.WatchlistId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface WatchlistRepository extends Repository<Watchlist, WatchlistId> {

  List<Watchlist> findAllByOrderByCreatedAtDesc();

  Optional<Watchlist> findById(WatchlistId id);

  boolean existsById(WatchlistId id);

  Watchlist save(Watchlist watchlist);

  void deleteById(WatchlistId id);
}
