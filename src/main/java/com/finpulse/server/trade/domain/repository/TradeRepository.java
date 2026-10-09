package com.finpulse.server.trade.domain.repository;

import com.finpulse.server.trade.domain.model.Trade;
import com.finpulse.server.trade.domain.model.TradeId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface TradeRepository extends Repository<Trade, TradeId> {

  List<Trade> findAllByOrderByCreatedAtDesc();

  Optional<Trade> findById(TradeId id);

  boolean existsById(TradeId id);

  Trade save(Trade trade);

  void deleteById(TradeId id);
}
