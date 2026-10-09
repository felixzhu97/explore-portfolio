package com.finpulse.server.option.domain.repository;

import com.finpulse.server.option.domain.model.Option;
import com.finpulse.server.option.domain.model.OptionId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface OptionRepository extends Repository<Option, OptionId> {

  List<Option> findAllByOrderByCreatedAtDesc();

  Optional<Option> findById(OptionId id);

  boolean existsById(OptionId id);

  Option save(Option option);

  void deleteById(OptionId id);
}
