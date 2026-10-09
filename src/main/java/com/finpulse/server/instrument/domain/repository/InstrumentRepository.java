package com.finpulse.server.instrument.domain.repository;

import com.finpulse.server.instrument.domain.model.Instrument;
import com.finpulse.server.instrument.domain.model.InstrumentId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface InstrumentRepository extends Repository<Instrument, InstrumentId> {

  List<Instrument> findAllByOrderByCreatedAtDesc();

  Optional<Instrument> findById(InstrumentId id);

  boolean existsById(InstrumentId id);

  Instrument save(Instrument instrument);

  void deleteById(InstrumentId id);
}
