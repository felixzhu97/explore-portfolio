package com.finpulse.server.bond.domain.repository;

import com.finpulse.server.bond.domain.model.Bond;
import com.finpulse.server.bond.domain.model.BondId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface BondRepository extends Repository<Bond, BondId> {

  List<Bond> findAllByOrderByCreatedAtDesc();

  Optional<Bond> findById(BondId id);

  boolean existsById(BondId id);

  Bond save(Bond bond);

  void deleteById(BondId id);
}
