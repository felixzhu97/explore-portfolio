package com.finpulse.server.marketdata.mapper;

import com.finpulse.server.marketdata.domain.model.MarketData;
import com.finpulse.server.marketdata.dto.MarketDataRequest;
import com.finpulse.server.marketdata.dto.MarketDataResponse;
import org.springframework.stereotype.Component;

@Component
public class MarketDataMapper {
  public MarketData toDomain(MarketDataRequest request) {
    return MarketData.createMarketData(
        request.getInstrumentId(),
        request.getTimestamp(),
        request.getOpen(),
        request.getHigh(),
        request.getLow(),
        request.getClose(),
        request.getVolume(),
        request.getChangePct());
  }

  public void apply(MarketDataRequest request, MarketData marketData) {
    marketData.updateMarketData(
        request.getInstrumentId(),
        request.getTimestamp(),
        request.getOpen(),
        request.getHigh(),
        request.getLow(),
        request.getClose(),
        request.getVolume(),
        request.getChangePct());
  }

  public MarketDataResponse toResponse(MarketData marketData) {
    return MarketDataResponse.builder()
        .dataId(marketData.getId().getValue())
        .instrumentId(marketData.getInstrumentId().getValue())
        .timestamp(marketData.getTimestamp())
        .open(marketData.getOpen())
        .high(marketData.getHigh())
        .low(marketData.getLow())
        .close(marketData.getClose())
        .volume(marketData.getVolume())
        .changePct(marketData.getChangePct())
        .build();
  }
}
