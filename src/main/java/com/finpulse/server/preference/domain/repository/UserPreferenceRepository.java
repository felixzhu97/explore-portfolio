package com.finpulse.server.preference.domain.repository;

import com.finpulse.server.preference.domain.model.PreferenceId;
import com.finpulse.server.preference.domain.model.UserPreference;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserPreferenceRepository extends Repository<UserPreference, PreferenceId> {

  List<UserPreference> findAllByOrderByUpdatedAtDesc();

  Optional<UserPreference> findById(PreferenceId id);

  boolean existsById(PreferenceId id);

  UserPreference save(UserPreference preference);

  void deleteById(PreferenceId id);
}
