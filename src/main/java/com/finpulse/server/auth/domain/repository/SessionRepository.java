package com.finpulse.server.auth.domain.repository;

import com.finpulse.server.auth.domain.model.Session;
import com.finpulse.server.auth.domain.model.SessionId;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface SessionRepository extends Repository<Session, SessionId> {

  Session save(Session session);

  Optional<Session> findByToken(String token);

  @Transactional
  void deleteByToken(String token);
}
