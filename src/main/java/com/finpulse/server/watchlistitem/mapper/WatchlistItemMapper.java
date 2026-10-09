package com.finpulse.server.watchlistitem.mapper;

import com.finpulse.server.watchlistitem.domain.model.WatchlistItem;
import com.finpulse.server.watchlistitem.dto.WatchlistItemRequest;
import com.finpulse.server.watchlistitem.dto.WatchlistItemResponse;
import org.springframework.stereotype.Component;

@Component
public class WatchlistItemMapper {
  public WatchlistItem toDomain(WatchlistItemRequest request) {
    return WatchlistItem.createWatchlistItem(request.getWatchlistId(), request.getInstrumentId());
  }

  public void apply(WatchlistItemRequest request, WatchlistItem item) {
    item.updateWatchlistItem(request.getWatchlistId(), request.getInstrumentId());
  }

  public WatchlistItemResponse toResponse(WatchlistItem item) {
    return WatchlistItemResponse.builder()
        .watchlistItemId(item.getId().getValue())
        .watchlistId(item.getWatchlistId().getValue())
        .instrumentId(item.getInstrumentId().getValue())
        .addedAt(item.getAddedAt())
        .build();
  }
}
