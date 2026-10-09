package com.finpulse.server.settlement.domain.repository;

import com.finpulse.server.settlement.domain.model.Settlement;
import com.finpulse.server.settlement.domain.model.SettlementId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface SettlementRepository extends Repository<Settlement, SettlementId> {

  List<Settlement> findAllByOrderByCreatedAtDesc();

  Optional<Settlement> findById(SettlementId id);

  boolean existsById(SettlementId id);

  Settlement save(Settlement settlement);

  void deleteById(SettlementId id);
}
