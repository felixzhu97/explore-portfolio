package com.finpulse.server.position.domain.repository;

import com.finpulse.server.position.domain.model.Position;
import com.finpulse.server.position.domain.model.PositionId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PositionRepository extends Repository<Position, PositionId> {

  List<Position> findAllByOrderByCreatedAtDesc();

  Optional<Position> findById(PositionId id);

  boolean existsById(PositionId id);

  Position save(Position position);

  void deleteById(PositionId id);
}
