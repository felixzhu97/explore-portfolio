package com.finpulse.server.watchlist.mapper;

import com.finpulse.server.watchlist.domain.model.Watchlist;
import com.finpulse.server.watchlist.dto.WatchlistRequest;
import com.finpulse.server.watchlist.dto.WatchlistResponse;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMapper {
  public Watchlist toDomain(WatchlistRequest request) {
    return Watchlist.createWatchlist(request.getCustomerId(), request.getName());
  }

  public void apply(WatchlistRequest request, Watchlist watchlist) {
    watchlist.updateWatchlist(request.getCustomerId(), request.getName());
  }

  public WatchlistResponse toResponse(Watchlist watchlist) {
    return WatchlistResponse.builder()
        .watchlistId(watchlist.getId().getValue())
        .customerId(watchlist.getCustomerId().getValue())
        .name(watchlist.getName())
        .createdAt(watchlist.getCreatedAt())
        .build();
  }
}
