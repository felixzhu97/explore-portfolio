package com.finpulse.server.marketdata.domain.repository;

import com.finpulse.server.marketdata.domain.model.MarketData;
import com.finpulse.server.marketdata.domain.model.MarketDataId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MarketDataRepository extends Repository<MarketData, MarketDataId> {

  List<MarketData> findAllByOrderByCreatedAtDesc();

  Optional<MarketData> findById(MarketDataId id);

  boolean existsById(MarketDataId id);

  MarketData save(MarketData marketData);

  void deleteById(MarketDataId id);
}
